package demo.springframework.beans.factory;

import demo.springframework.beans.BeanException;

/**
 * Bean Factory
 * 定义了各种获取Bean的方法，判断Bean是否存在
 *
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
