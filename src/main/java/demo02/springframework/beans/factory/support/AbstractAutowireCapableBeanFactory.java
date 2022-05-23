package demo02.springframework.beans.factory.support;

import demo02.springframework.beans.factory.config.BeanDefinition;

/**
 * @ClassName AbstractSAutowireCapableBeanFactory
 * @Description 创造Object bean，并将其缓存到单例缓存池中
 * @Author gyf
 * @Date 2022/5/22
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
	@Override
	public Object createBean(String beanName, BeanDefinition beanDefinition) {
		Object bean = null;
		try {
			bean = beanDefinition.getBeanClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		addSingleton(beanName, beanDefinition);
		return bean;
	}
}
