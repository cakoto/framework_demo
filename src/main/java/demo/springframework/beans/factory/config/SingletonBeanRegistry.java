package demo.springframework.beans.factory.config;

/**
 * 单例注册
 *
 * @ClassName SingletonBeanRegistry
 * @Description 单例注册，接口 规定模式
 * @Author gyf
 * @Date 2022/5/22
 **/
public interface SingletonBeanRegistry {
	Object getSingleton(String beanName);
}
