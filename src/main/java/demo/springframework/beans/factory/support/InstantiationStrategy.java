package demo.springframework.beans.factory.support;

import demo.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @ClassName InstantiationStrategy
 * @Description 接口，定义实例化的行为，子类进行实现
 * @Author gyf
 * @Date 2022/5/23
 **/
public interface InstantiationStrategy {
	Object instantiate(BeanDefinition beanDefinition, String beanName,
	                   Constructor<?> constructor,  Object[] args);
}
