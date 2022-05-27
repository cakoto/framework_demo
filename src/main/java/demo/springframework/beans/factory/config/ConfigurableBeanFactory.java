package demo.springframework.beans.factory.config;

import demo.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 获取BeanClassLoader、BeanPostProcessor的配置化接口。
 * 完成了对BeanFactory的各种配置及销毁方法
 *
 * @ClassName ConfigurableBeanRegistry
 * @Description 获取BeanClassLoader、BeanPostProcessor的配置化接口
 * @Author gyf
 * @Date 2022/5/24
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

	String SCOPE_SINGLETON = "singleton";

	String SCOPE_PROTOTYPE = "prototype";

	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

	void destroySingletons();

}
