package demo.springframework.beans.factory;

/**
 * @ClassName BeanFactory
 * @Description Bean工厂接口，定义了行为getBean
 * @Author gyf
 * @Date 2022/5/22
 **/
public interface BeanFactory {
	public Object getBean(String beanName);
	public Object getBean(String beanName, Object... args);
}
