package demo.springframework.context;

import demo.springframework.beans.BeanException;

/**
 * 提供refresh的方法，spring中的核心方法，整个容器就是通过该方法,完成所有的 bean 的创建以及初始化
 *
 * @ClassName ConfigurableApplicationContext
 * @Description 提供refresh的方法，spring中的核心方法，整个容器就是通过该方法,完成所有的 bean 的创建以及初始化
 * @Author gyf
 * @Date 2022/5/25
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{
	/**
	 * 刷新容器/启动整个容器
	 *
	 * @throws BeanException bean exception
	 */
	void refresh() throws BeanException;
}
