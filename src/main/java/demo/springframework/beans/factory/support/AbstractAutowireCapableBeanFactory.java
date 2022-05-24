package demo.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import demo.springframework.beans.BeanException;
import demo.springframework.beans.PropertyValue;
import demo.springframework.beans.PropertyValues;
import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @ClassName AbstractSAutowireCapableBeanFactory
 * @Description 创造Object bean，并将其缓存到单例缓存池中
 * @Author gyf
 * @Date 2022/5/22
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

	private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

	@Override
	public Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition, beanName, args);
			applyPropertyValues(beanName, bean, beanDefinition);
		} catch (Exception e) {
			throw new BeanException("Instantiation of bean failed", e);
		}
		addSingleton(beanName, bean);
		return bean;
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
}
