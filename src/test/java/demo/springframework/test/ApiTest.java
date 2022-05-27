package demo.springframework.test;

import cn.hutool.core.io.IoUtil;
import demo.springframework.beans.PropertyValue;
import demo.springframework.beans.PropertyValues;
import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.config.BeanReference;
import demo.springframework.beans.factory.support.DefaultListableBeanFactory;
import demo.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import demo.springframework.context.support.ClassPathXmlApplicationContext;
import demo.springframework.core.io.DefaultResourceLoader;
import demo.springframework.core.io.Resource;
import demo.springframework.test.bean.UserDao;
import demo.springframework.test.bean.UserService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName ApiTest
 * @Description 单元测试
 * @Author gyf
 * @Date 2022/5/23
 **/
public class ApiTest {

	private DefaultResourceLoader resourceLoader;

	/**
	 * 有参创造bean对象
	 */
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

	/**
	 * 为bean对象中注入属性
	 */
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

	/**
	 * 初始化ResourceLoader
	 */
	@Before
	public void init() {
		resourceLoader = new DefaultResourceLoader();
	}

	@Test
	public void test_classpath() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:important.properties");
		testResourceMethod(resource);
	}

	@Test
	public void test_file() throws IOException {
		Resource resource = resourceLoader.getResource("src/test/java/demo/springframework/test/resource/important.properties");
		testResourceMethod(resource);
	}

	@Test
	public void test_url() throws IOException {
		Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/small-spring-step-05/src/test/resources/important.properties");
		testResourceMethod(resource);
	}

	private void testResourceMethod(Resource resource) throws IOException {
		InputStream inputStream = resource.getInputStream();
		String content = IoUtil.readUtf8(inputStream);
		System.out.println(content);
	}

	@Test
	public void test_xml() {
		// 1. 初始化beanFactory
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		// 2. 读取配置文件
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("classpath:spring.xml");

		// 3. 获取bean对象
		UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
		userService.queryUserInfo();
	}

	@Test
	public void test_xmlWithContext() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		UserService userService = (UserService) applicationContext.getBean("userService", UserService.class);
		userService.queryUserInfo();
	}

	@Test
	public void test_xmlWithInitAndDestroy() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		applicationContext.registerShutdownHook();
		UserService userService = applicationContext.getBean("userService", UserService.class);
		userService.queryUserInfo();
	}

}
