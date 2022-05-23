package demo.springframework.beans.factory.support;

import demo.springframework.beans.factory.config.BeanDefinition;

/**
 * @ClassName BeanDefinitionRegistry
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public interface BeanDefinitionRegistry {
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
