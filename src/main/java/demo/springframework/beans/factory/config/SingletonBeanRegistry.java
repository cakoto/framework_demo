package demo.springframework.beans.factory.config;

/**
 * @ClassName SingletonBeanRegistry
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public interface SingletonBeanRegistry {
	Object getSingleton(String beanName);
}
