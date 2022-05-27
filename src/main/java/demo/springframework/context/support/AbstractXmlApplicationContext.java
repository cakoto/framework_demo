package demo.springframework.context.support;

import demo.springframework.beans.factory.support.DefaultListableBeanFactory;
import demo.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import demo.springframework.context.ApplicationContext;

/**
 * 用于{@link ApplicationContext}实现的方便基类，从包含
 * {@link XmlBeanDefinitionReader}理解的bean定义的XML文档中获取配置。
 *
 * @ClassName AbstractXmlApplicationContext
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/25
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
	@Override
	protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		String[] configLocations = getConfigLocations();
		if (null != configLocations) {
			reader.loadBeanDefinitions(configLocations);
		}
	}

	protected abstract String[] getConfigLocations();
}
