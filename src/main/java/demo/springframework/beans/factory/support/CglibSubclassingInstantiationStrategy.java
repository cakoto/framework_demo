package demo.springframework.beans.factory.support;

import demo.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * [关于cglib的教程](http://c.biancheng.net/view/4271.html)
 *
 * @ClassName CglibSubclassingInstantiationStrategy
 * @Description 通过Cglib的方式生成子类的策略
 * @Author gyf
 * @Date 2022/5/23
 **/
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
	@Override
	public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) {
		Enhancer enhancer = new Enhancer();
		// 1.确定要增强的类
		enhancer.setSuperclass(beanDefinition.getBeanClass());
		// 2.添加回调函数
		enhancer.setCallback(new NoOp() {
			@Override
			public int hashCode() {
				return super.hashCode();
			}
		});
		// 3.创建类
		if(null != constructor) {
			return enhancer.create(constructor.getParameterTypes(), args);
		}
		return enhancer.create();
	}
}
