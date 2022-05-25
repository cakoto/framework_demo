package demo.springframework.beans.factory;

import demo.springframework.beans.BeanException;

/**
 * @ClassName BeanFactory
 * @Description Bean工厂接口，定义了行为getBean
 * @Author gyf
 * @Date 2022/5/22
 **/
public interface BeanFactory {
	public Object getBean(String beanName);
	public Object getBean(String beanName, Object... args);
	<T> T getBean(String name, Class<T> requiredType) throws BeanException;
}
