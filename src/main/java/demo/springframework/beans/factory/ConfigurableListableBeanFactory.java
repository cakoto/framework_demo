package demo.springframework.beans.factory;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.config.AutowireCapableBeanFactory;
import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.config.BeanPostProcessor;
import demo.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * BeanFactory的配置清单类，定义了忽略的类型、通过BeanName获取BeanDefinition等方法。
 * 由大多数bean工厂实现的配置接口。 提供分析和修改bean定义以及预实例化单例的工具。
 *
 * @ClassName ConfigurableListableBeanFactory
 * @Description 分析和修改Bean及预先实例化的接口
 * @Author gyf
 * @Date 2022/5/24
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

	BeanDefinition getBeanDefinition(String beanName) throws BeanException;

	void preInstantiateSingletons() throws BeanException;

	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
