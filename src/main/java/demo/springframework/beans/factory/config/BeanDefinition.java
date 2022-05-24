package demo.springframework.beans.factory.config;

import demo.springframework.beans.PropertyValues;

/**
 * @ClassName BeanDefinition
 * @Description Bean的定义
 * @Author gyf
 * @Date 2022/5/22
 **/
public class BeanDefinition {
	private final PropertyValues propertyValues;
	private Class<?> beanClass;

	public BeanDefinition(Class<?> beanClass) {
		this.beanClass = beanClass;
		this.propertyValues = new PropertyValues();
	}

	public BeanDefinition(Class<?> beanClass, PropertyValues propertyValues) {
		this.beanClass = beanClass;
		this.propertyValues = propertyValues == null ? new PropertyValues(): propertyValues;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}
}
