package demo.springframework.beans.factory.config;

/**
 * @ClassName BeanPostProcessor
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/25
 **/
public interface BeanPostProcessor {

	/**
	 * 在create bean obj之前(执行初始化方法前)执行
	 *
	 * @param bean bean
	 * @param beanName bean name
	 */
	Object postProcessBeforeInitialization(Object bean, String beanName);

	/**
	 * 在执行bean的初始化方法之后执行此方法
	 *
	 * @param bean bean
	 * @param beanName bean name
	 */
	Object postProcessAfterInitialization(Object bean, String beanName);
}
