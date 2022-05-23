package demo02.springframework.beans.factory.config;

/**
 * @ClassName BeanDefinition
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public class BeanDefinition {
	private Class beanClass;

	public BeanDefinition(Class beanClass) {
		this.beanClass = beanClass;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}
}
