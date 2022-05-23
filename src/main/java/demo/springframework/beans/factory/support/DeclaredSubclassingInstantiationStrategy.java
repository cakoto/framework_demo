package demo.springframework.beans.factory.support;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName DeclareSubclassingInstantiationStrategy
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/23
 **/
public class DeclaredSubclassingInstantiationStrategy implements InstantiationStrategy{
	@Override
	public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeanException {
		Class<?> clazz = beanDefinition.getBeanClass();

		try {
			// 如果为空则是无参构造，否则为有参构造
			if (null != constructor) {
				return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
			} else {
				return clazz.getDeclaredConstructor().newInstance();
			}
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new BeanException("Failed to instantiate ["+ clazz.getName()+"]", e);
		}
	}
}
