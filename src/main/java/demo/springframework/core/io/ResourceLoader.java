package demo.springframework.core.io;

/**
 * 资源加载器接口，定义了getResource的方法
 *
 * @ClassName ResourceLoader
 * @Description 定义获取资源接口 传递location即可
 * @Author gyf
 * @Date 2022/5/24
 **/
public interface ResourceLoader {
	String CLASSPATH_URL_PREFIX = "classpath:";

	Resource getResource(String location);
}
