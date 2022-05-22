package demo.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BeanFactory
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public class BeanFactory {
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(name, beanDefinition);
	}
}
