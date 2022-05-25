package demo.springframework.beans.factory.config;

import demo.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

/**
 * @ClassName AutowireCapableBeanFactory
 * @Description 自动化处理Bean工厂配置
 * @Author gyf
 * @Date 2022/5/24
 **/
public class AutowireCapableBeanFactory extends AbstractAutowireCapableBeanFactory {
	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		System.out.println("hello");
		return null;
	}
}
