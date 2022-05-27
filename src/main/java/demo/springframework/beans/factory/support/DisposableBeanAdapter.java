package demo.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.DisposableBean;
import demo.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * Adapter适配器模式 销毁方法
 * @ClassName DisposableBeanAdapter
 * @Description 销毁方法适配器
 * @Author gyf
 * @Date 2022/5/27
 **/
public class DisposableBeanAdapter implements DisposableBean {

	private final Object bean;
	private final String beanName;
	private String destroyMethodName;

	public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
		this.bean = bean;
		this.beanName = beanName;
		this.destroyMethodName = beanDefinition.getDestroyMethodName();
	}

	@Override
	public void destroy() throws Exception {
		// 1.实现接口DisposableBean
		if (bean instanceof DisposableBean) {
			((DisposableBean) bean).destroy();
		}

		// 2. 判断配置信息 destroy-method是否存在
		// 如果存在则反射调用那个方法，否则不执行
		if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
			Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
			if (null == destroyMethod) {
				throw new BeanException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
			}
			destroyMethod.invoke(bean);
		}
	}
}
