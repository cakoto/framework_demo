package demo02.springframework.beans.factory.support;

import demo02.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DefaultListableBeanFactory
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

	// 容器桶
	private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		// if (beanDefinition == null)
		return beanDefinitionMap.get(beanName);
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(beanName, beanDefinition);
	}
}
