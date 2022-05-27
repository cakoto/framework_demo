package demo.springframework.context.support;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.ConfigurableListableBeanFactory;
import demo.springframework.beans.factory.config.BeanFactoryPostProcessor;
import demo.springframework.beans.factory.config.BeanPostProcessor;
import demo.springframework.context.ApplicationContext;
import demo.springframework.context.ConfigurableApplicationContext;
import demo.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 接口{@link ApplicationContext}的抽象实现。 没有强制要求配置使用的存储类型;
 * 简单地实现了通用上下文功能。 使用模板方法设计模式，需要具体的子类来实现抽象方法。
 *
 * @ClassName AbstractApplicationContext
 * @Description Context抽象类
 * @Author gyf
 * @Date 2022/5/25
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
	/**
	 * 模板设计模式
	 * 具体实现步骤如下所示
	 *
	 * @throws BeanException bean exception
	 */
	@Override
	public void refresh() throws BeanException {
		// step1: 创建BeanFactory并加载BeanDefinition
		refreshBeanFactory();

		// step2: 获取BeanFactory
		ConfigurableListableBeanFactory beanFactory = getBeanFactory();

		// step3: 实例化前执行BeanFactoryPostProcessor
		invokeBeanFactoryPostProcessors(beanFactory);

		// step4: BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
		registerBeanFactoryPostProcessors(beanFactory);

		// step5: 提前实例化单例Bean对象
		beanFactory.preInstantiateSingletons();
	}

	@Override
	public void registerShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(this::close));
	}

	@Override
	public void close() {
		getBeanFactory().destroySingletons();
	}

	private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
		Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
		for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
			beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
		}
	}

	private void registerBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
		Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
		for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
			beanFactory.addBeanPostProcessor(beanPostProcessor);
		}
	}

	protected abstract ConfigurableListableBeanFactory getBeanFactory();

	protected abstract void refreshBeanFactory();

	@Override
	public Object getBean(String beanName) {
		return getBeanFactory().getBean(beanName);
	}

	@Override
	public Object getBean(String beanName, Object... args) {
		return getBeanFactory().getBean(beanName, args);
	}

	@Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
		return getBeanFactory().getBean(name, requiredType);
	}

	@Override
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
		return getBeanFactory().getBeansOfType(type);
	}

	@Override
	public String[] getBeanDefinitionNames() {
		return getBeanFactory().getBeanDefinitionNames();
	}
}
