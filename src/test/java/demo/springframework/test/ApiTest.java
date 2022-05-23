package demo.springframework.test;

import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.support.DefaultListableBeanFactory;
import demo.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @ClassName ApiTest
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/23
 **/
public class ApiTest {

	@Test
	public void testBeanFactory() {
		// 1. 初始化工厂
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2. 定义bean
		BeanDefinition userBeanDefinition = new BeanDefinition(UserService.class);

		// 3. 注册bean
		beanFactory.registerBeanDefinition("UserService", userBeanDefinition);

		// 4. 第一次获取bean
		UserService userService = (UserService) beanFactory.getBean("UserService", "鸟人");
		userService.queryInfo();

		// 5. 后续获取bean(单例)
		UserService userServiceSingleton = (UserService) beanFactory.getBean("UserService");
		userServiceSingleton.queryInfo();
	}
}
