package demo.springframework.beans.factory.support;

import demo.springframework.core.io.DefaultResourceLoader;
import demo.springframework.core.io.ResourceLoader;

/**
 * @ClassName AbstractBeanDefinitionReader
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/24
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

	private final BeanDefinitionRegistry registry;

	private final ResourceLoader resourceLoader;

	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		this.registry = registry;
		this.resourceLoader = resourceLoader;
	}

	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this(registry, new DefaultResourceLoader());
	}

	@Override
	public BeanDefinitionRegistry getRegistry() {
		return registry;
	}

	@Override
	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
}
