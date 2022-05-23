package demo02.springframework.beans.factory.support;

import demo02.springframework.beans.factory.config.BeanDefinition;

/**
 * @ClassName BeanDefinitionRegistry
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public interface BeanDefinitionRegistry {
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
