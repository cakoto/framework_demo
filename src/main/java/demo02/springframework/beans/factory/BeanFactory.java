package demo02.springframework.beans.factory;

/**
 * @ClassName BeanFactory
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public interface BeanFactory {
	public Object getBean(String beanName);
}
