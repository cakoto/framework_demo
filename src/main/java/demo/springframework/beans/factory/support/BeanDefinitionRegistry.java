package demo.springframework.beans.factory.support;

import demo.springframework.beans.factory.config.BeanDefinition;

/**
 * @ClassName BeanDefinitionRegistry
 * @Description 注册中心
 * @Author gyf
 * @Date 2022/5/22
 **/
public interface BeanDefinitionRegistry {
	/**
	 * 向注册表中注册BeanDefinition
	 * @param beanName bean name
	 * @param beanDefinition bean的定义
	 */
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	/**
	 * 判断是否包含指定名称的BeanDefinition
	 * 就是判断这个是否已经注册了
	 * @param beanName bean的名字
	 * @return true/false
	 */
	boolean containsBeanDefinition(String beanName);

	/**
	 * use bean name query bean definition
	 *
	 * @param beanName bean name
	 * @return bean definition
	 */
	BeanDefinition getBeanDefinition(String beanName);

	/**
	 * 返回注册表中所有Bean的名称
	 *
	 * @return bean definition array
	 */
	String[] getBeanDefinitionName();

}
