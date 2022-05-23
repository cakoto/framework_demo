package demo02.springframework.beans.factory.support;

import demo02.springframework.beans.BeanException;
import demo02.springframework.beans.factory.BeanFactory;
import demo02.springframework.beans.factory.config.BeanDefinition;

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
	public Object getBean(String beanName)  {
		Object bean = getSingleton(beanName);
		if (bean != null) {
			return bean;
		}

		BeanDefinition beanDefinition = getBeanDefinition(beanName);
		return createBean(beanName, beanDefinition);
	}

	public abstract BeanDefinition getBeanDefinition(String beanName);
	public abstract Object createBean(String beanName, BeanDefinition beanDefinition);

}
