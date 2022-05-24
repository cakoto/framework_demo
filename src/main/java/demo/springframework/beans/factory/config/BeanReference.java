package demo.springframework.beans.factory.config;

/**
 * @ClassName BeanReference
 * @Description Bean 的引用
 * @Author gyf
 * @Date 2022/5/23
 **/
public class BeanReference {
	private final String beanName;

	public BeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}
