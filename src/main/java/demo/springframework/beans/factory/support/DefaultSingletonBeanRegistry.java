package demo.springframework.beans.factory.support;

import demo.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DefaultSingletonBeanRegistry
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
	private final Map<String, Object> singletonObjects = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	protected void addSingleton(String beanName, Object bean) {
		singletonObjects.put(beanName, bean);
	}
}
