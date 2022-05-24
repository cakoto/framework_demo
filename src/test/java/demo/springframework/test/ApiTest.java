package demo.springframework.test;

import demo.springframework.beans.PropertyValue;
import demo.springframework.beans.PropertyValues;
import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.config.BeanReference;
import demo.springframework.beans.factory.support.DefaultListableBeanFactory;
import demo.springframework.test.bean.UserDao;
import demo.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @ClassName ApiTest
 * @Description 单元测试
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
	@Test
	public void test_BeanFactory() {
		// 1.初始化 BeanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2. UserDao 注册
		beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

		// 3. UserService 设置属性[uId、userDao]
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
		propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

		// 4. 向 UserService 注入属性
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
		beanFactory.registerBeanDefinition("userService", beanDefinition);

		// 5. UserService 获取bean
		UserService userService = (UserService) beanFactory.getBean("userService");
		userService.queryUserInfo();
	}
}
