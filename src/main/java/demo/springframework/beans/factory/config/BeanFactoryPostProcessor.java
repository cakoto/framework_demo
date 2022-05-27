package demo.springframework.beans.factory.config;

import demo.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @ClassName BeanFactoryPostProcessor
 * @Description 在BeanDefinition加载完成后提供修改BeanDefinition的支持
 * @Author gyf
 * @Date 2022/5/25
 **/
public interface BeanFactoryPostProcessor {
	/**
	 * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
	 *
	 * @param factory bean factory
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory factory);
}
