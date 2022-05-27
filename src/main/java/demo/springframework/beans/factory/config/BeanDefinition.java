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

	// xxx.xml中bean的initMethod会存到这里
	private String initMethodName;

	// xxx.xml中bean的destroyMethod会存到这里
	private String destroyMethodName;

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

	public String getInitMethodName() {
		return initMethodName;
	}

	public void setInitMethodName(String initMethodName) {
		this.initMethodName = initMethodName;
	}

	public String getDestroyMethodName() {
		return destroyMethodName;
	}

	public void setDestroyMethodName(String destroyMethodName) {
		this.destroyMethodName = destroyMethodName;
	}

}
