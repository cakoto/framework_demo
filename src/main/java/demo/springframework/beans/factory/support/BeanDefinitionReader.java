package demo.springframework.beans.factory.support;

import demo.springframework.beans.BeanException;
import demo.springframework.core.io.Resource;
import demo.springframework.core.io.ResourceLoader;

/**
 * @ClassName BeanDefinitionReader
 * @Description 读取接口
 * @Author gyf
 * @Date 2022/5/24
 **/
public interface BeanDefinitionReader {

	// 注册
	BeanDefinitionRegistry getRegistry();

	// 加载
	ResourceLoader getResourceLoader();

	void loadBeanDefinitions(Resource resource) throws BeanException;

	void loadBeanDefinitions(Resource... resources) throws BeanException;

	void loadBeanDefinitions(String location) throws BeanException;

}
