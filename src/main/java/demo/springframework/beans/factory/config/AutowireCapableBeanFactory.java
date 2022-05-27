package demo.springframework.beans.factory.config;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.BeanFactory;

/**
 * 在BeanFactory基础上提供了Bean的创建、配置、注入、销毁等功能。
 * 扩展{@link BeanFactory}接口，让能够自动装配的bean工厂实现，前提是它们希望为现有的bean实例公开该功能。
 *
 * @ClassName AutowireCapableBeanFactory
 * @Description 自动化处理Bean工厂配置
 * @Author gyf
 * @Date 2022/5/24
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {
	/**
	 * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
	 *
	 * @param existingBean
	 * @param beanName
	 * @return
	 * @throws BeanException
	 */
	Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeanException;

	/**
	 * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
	 *
	 * @param existingBean
	 * @param beanName
	 * @return
	 * @throws BeanException
	 */
	Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeanException;
}
