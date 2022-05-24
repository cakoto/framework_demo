package demo.springframework.beans.factory.support;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.BeanFactory;
import demo.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象类定义模板方法
 * @ClassName AbstractBeanFactory
 * @Description 抽象类
 * @Author gyf
 * @Date 2022/5/22
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

	/**
	 * 检查单例对象是否已经缓存，如果缓存则直接返回结果，否则创建新的bean
	 * @param beanName bean的名字
	 * @return  bean实例化后的对象
	 * @throws BeanException Bean异常
	 */
	@Override
	public Object getBean(String beanName)  throws BeanException  {
		return doGetBean(beanName, null);
	}

	@Override
	public Object getBean(String beanName, Object... args)  throws BeanException {
		return doGetBean(beanName, args);
	}

	// 泛型
	public Object doGetBean(String beanName, final Object[] args) {
		Object bean = getSingleton(beanName);
		if (bean != null) {
			return bean;
		}

		BeanDefinition beanDefinition = getBeanDefinition(beanName);
		return createBean(beanName, beanDefinition, args);
	}

	public abstract BeanDefinition getBeanDefinition(String beanName);
	public abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);
	public abstract void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition);

}
