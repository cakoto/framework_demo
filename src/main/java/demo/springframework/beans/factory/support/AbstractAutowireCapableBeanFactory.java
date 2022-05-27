package demo.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import demo.springframework.beans.BeanException;
import demo.springframework.beans.PropertyValue;
import demo.springframework.beans.PropertyValues;
import demo.springframework.beans.factory.DisposableBean;
import demo.springframework.beans.factory.InitializingBean;
import demo.springframework.beans.factory.config.AutowireCapableBeanFactory;
import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.config.BeanPostProcessor;
import demo.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 创造Object bean，并将其缓存到单例缓存池中
 * @ClassName AbstractSAutowireCapableBeanFactory
 * @Description 创造Object bean，并将其缓存到单例缓存池中
 * @Author gyf
 * @Date 2022/5/22
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

	private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

	/**
	 * 核心方法
	 * 创建一个bean实例，并将属性进行填充，执行前后处理等操作
	 */
	@Override
	public Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition, beanName, args);
			applyPropertyValues(beanName, bean, beanDefinition);
			bean = initializeBean(beanName, bean, beanDefinition);
		} catch (Exception e) {
			throw new BeanException("Instantiation of bean failed", e);
		}
		// 注册实现了 disposableBean 接口的bean对象
		registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
		addSingleton(beanName, bean);
		return bean;
	}

	protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
		if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
			registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
		}
	}


	protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
		Constructor<?> constructorToUser = null;
		Class<?> beanClass = beanDefinition.getBeanClass();
		Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
		// 循环对比构造函数参数集合declaredConstructors和入参信息args的匹配情况
		for (Constructor<?> constructor: declaredConstructors) {
			if (null != args && constructor.getParameterTypes().length == args.length) {
				constructorToUser = constructor;
				break;
			}
		}
		return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUser, args);
	}

	/**
	 * 属性包含了基本属性(Integer、String)还可能包含了Bean的对象类型
	 *
	 * @param beanName          bean对象的名称
	 * @param bean              bean对象
	 * @param beanDefinition    bean对象的定义（Class和属性list）
	 */
	@Override
	public void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
		PropertyValues propertyValues = beanDefinition.getPropertyValues();
		for (PropertyValue propertyValue: propertyValues.getPropertyValues()) {
			String name = propertyValue.getName();
			Object value = propertyValue.getValue();

			if(value instanceof BeanReference) {
				// A依赖B, 将B实例化后进行填充
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getBeanName());
			}
			// 属性填充
			// 设置字段值，通过反射设置字段值，并不调用setXXX方法
			// 对象同样支持Map类型，name即为key
			BeanUtil.setFieldValue(bean, name, value);
		}
	}

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}

	/**
	 * 具体的步骤：
	 * 1. 创建BPP对象
	 * 2. 创建Bean对象
	 * 3. 执行BPPBeforeInit
	 * 4. 执行Bean对象初始化
	 * 5. 执行BPPAfterInit
	 * wrappedBean其实也是一个Bean，但是是在bean前后进行了处理
	 */
	private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

		// Note:BeanPostProcessor是对象创建后的处理器
		// 1. 执行BeanPostProcessor Before Initialize进行处理(初始化前)
		Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

		// 2. 执行Bean对象的初始化方法，进行初始化
		try {
			invokeInitMethods(beanName, wrappedBean, beanDefinition);
		} catch (Exception e) {
			throw new BeanException("Invocation of init method of bean[" + beanName + "] failed", e);
		}

		// 3. 执行BeanPostProcessor After Initialize进行处理(初始化后)
		wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
		return wrappedBean;
	}

	private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws Exception {

		// 1. 实现接口InitializingBean
		if (wrappedBean instanceof InitializingBean) {
			((InitializingBean) wrappedBean).afterPropertiesSet();
		}

		// 2. 判断配置信息init-method是否存在
		// 如果存在则反射调用那个方法，否则不执行
		String initMethodName = beanDefinition.getInitMethodName();
		if (StrUtil.isNotEmpty(initMethodName) && !(wrappedBean instanceof InitializingBean)) {
			Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
			if (null == initMethod) {
				throw new BeanException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
			}
			initMethod.invoke(wrappedBean);
		}
	}

	@Override
	public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException {
		Object bean = existingBean;
		// 获取所有的BeanPostProcessor进行遍历
		for (BeanPostProcessor processor:getBeanPostProcessors()) {
			// 执行创建前的操作
			Object current = processor.postProcessBeforeInitialization(bean, beanName);
			if (current == null) return bean;
			bean = current;
		}
		return bean;
	}

	@Override
	public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException {
		Object bean = existingBean;
		for (BeanPostProcessor processor:getBeanPostProcessors()) {
			Object current = processor.postProcessAfterInitialization(bean, beanName);
			if (current == null) return bean;
			bean = current;
		}
		return bean;
	}

}
