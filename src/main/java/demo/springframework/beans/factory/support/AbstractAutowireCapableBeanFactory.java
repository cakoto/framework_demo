package demo.springframework.beans.factory.support;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.config.BeanDefinition;

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
	public Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition, beanName, args);
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

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}
}
