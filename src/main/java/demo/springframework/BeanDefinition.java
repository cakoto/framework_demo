package demo.springframework;

/**
 * @ClassName BeanDefinition
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public class BeanDefinition {
	private Object bean;

	public BeanDefinition(Object bean) {
		this.bean = bean;
	}

	public Object getBean() {
		return bean;
	}
}
