package demo.springframework.context.support;

import demo.springframework.beans.factory.ConfigurableListableBeanFactory;
import demo.springframework.beans.factory.support.DefaultListableBeanFactory;
import demo.springframework.context.ApplicationContext;

/**
 * {@link ApplicationContext}实现的基类，该实现支持对{@link #refresh()}的多次
 * 调用，每次都创建一个新的内部bean工厂实例。通常(但不一定)，这样的上下文将由一组
 * 配置位置驱动，以便从中加载bean定义。
 *
 * @ClassName AbstractRefreshableApplicationContext
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/25
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

	private DefaultListableBeanFactory beanFactory;

	@Override
	protected void refreshBeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		loadBeanDefinitions(beanFactory);
		this.beanFactory = beanFactory;
	}

	protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

	@Override
	protected ConfigurableListableBeanFactory getBeanFactory() {
		return beanFactory;
	}
}
