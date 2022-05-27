package demo.springframework.beans.factory;

import demo.springframework.beans.BeanException;

import java.util.Map;

/**
 * 拓展了Bean的查询方法，有根据类型获取BeanName等功能
 * 对{@link BeanFactory}接口的扩展，以由bean工厂实现，这些bean
 * 工厂可以枚举它们的所有bean实例，而不是按照客户机的请求逐个进行
 * bean查找。 预加载所有bean定义的BeanFactory实现(例如基于xml的
 * 工厂)可以实现这个接口。
 *
 * @ClassName ListableBeanFactory
 * @Description 返回
 * @Author gyf
 * @Date 2022/5/24
 **/
public interface ListableBeanFactory extends BeanFactory {
	/**
	 * 根绝type的类型返回bean的实例
	 *
	 * @param type type
	 * @param <T>  -
	 * @return bean实例集合
	 * @throws BeanException bean exception
	 */
	<T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

	/**
	 * @return 注册表中所有Bean的名称(Bean name也就是map的key)
	 */
	String[] getBeanDefinitionNames();
}
