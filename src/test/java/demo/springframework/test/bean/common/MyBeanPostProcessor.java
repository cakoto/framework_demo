package demo.springframework.test.bean.common;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.config.BeanPostProcessor;
import demo.springframework.test.bean.UserService;

/**
 * @ClassName MyBeanPostProcessor
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/25
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
		if ("userService".equals(beanName)) {
			UserService userService = (UserService) bean;
			userService.setLocation("改为：北京");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
		return bean;
	}

}

