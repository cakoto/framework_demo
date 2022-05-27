package demo.springframework.beans.factory.support;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.config.BeanPostProcessor;
import demo.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类定义模板方法，实现了ConfigurableBeanFactory的接口
 * @ClassName AbstractBeanFactory
 * @Description 抽象类
 * @Author gyf
 * @Date 2022/5/22
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

	private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
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

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
		return (T)getBean(name);
	}

	// 泛型
	@SuppressWarnings("unchecked")
	public <T> T doGetBean(final String beanName, final Object[] args) {
		Object bean = getSingleton(beanName);
		if (bean != null) {
			return (T)bean;
		}

		BeanDefinition beanDefinition = getBeanDefinition(beanName);
		return (T)createBean(beanName, beanDefinition, args);
	}

	protected abstract BeanDefinition getBeanDefinition(String beanName);
	protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);
	protected abstract void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition);

	@Override
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		this.beanPostProcessors.remove(beanPostProcessor);
		this.beanPostProcessors.add(beanPostProcessor);
	}

	/**
	 * Return the list of BeanPostProcessors that will get applied
	 * to beans created with this factory.
	 */
	public List<BeanPostProcessor> getBeanPostProcessors() {
		return this.beanPostProcessors;
	}
}
