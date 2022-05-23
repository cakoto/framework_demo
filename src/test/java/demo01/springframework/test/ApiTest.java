package demo01.springframework.test;

import demo01.springframework.BeanDefinition;
import demo01.springframework.BeanFactory;
import demo01.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @ClassName ApiTest
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public class ApiTest {

	@Test
	public void testBeanFactory() {
		// 1. 初始化Bean工厂
		BeanFactory beanFactory = new BeanFactory();

		// 2. 定义Bean
		BeanDefinition userServiceDefinition = new BeanDefinition(new UserService());

		// 3. 注册Bean
		beanFactory.registerBeanDefinition("userService", userServiceDefinition);

		// 4. 获取Bean(返回的是Object，要强转一下
		UserService userService = (UserService) beanFactory.getBean("userService");
		userService.queryInfo();
	}
}
