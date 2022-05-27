package demo.springframework.beans.factory.support;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.ConfigurableListableBeanFactory;
import demo.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean Factory的默认实现，可对其进行拓展实现自定义bean工厂
 * 其是一个完整的ioc容器，需求复杂时可以拓展这个类进行ioc实现
 *
 * @ClassName DefaultListableBeanFactory
 * @Description Bean Factory的默认实现
 * @Author gyf
 * @Date 2022/5/22
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

	// 容器桶
	private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		if (beanDefinition == null) throw new BeanException("No bean named '" + beanName + "' is defined");
		return beanDefinition;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(beanName, beanDefinition);
	}

	@Override
	public boolean containsBeanDefinition(String beanName) {
		return beanDefinitionMap.containsKey(beanName);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
		Map<String, T> res = new HashMap<>();
		beanDefinitionMap.forEach((beanName, beanDefinition) -> {
			Class<?> beanClass = beanDefinition.getBeanClass();
			if (type.isAssignableFrom(beanClass)) {
				res.put(beanName, (T)getBean(beanName));
			}
		});
		return res;
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return beanDefinitionMap.keySet().toArray(new String[0]);
	}

	@Override
	public void preInstantiateSingletons() throws BeanException {
		beanDefinitionMap.keySet().forEach(this::getBean);
	}
}
