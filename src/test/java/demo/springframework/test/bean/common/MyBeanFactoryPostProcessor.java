package demo.springframework.test.bean.common;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.PropertyValue;
import demo.springframework.beans.PropertyValues;
import demo.springframework.beans.factory.ConfigurableListableBeanFactory;
import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @ClassName MyBeanFactoryPostProcessor
 * @Description 在工厂建立前完成
 * @Author gyf
 * @Date 2022/5/25
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {

		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
		PropertyValues propertyValues = beanDefinition.getPropertyValues();

		propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
	}

}

