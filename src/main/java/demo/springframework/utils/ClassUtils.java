package demo.springframework.utils;

/**
 * @ClassName ClassUtils
 * @Description 获取ClassLoader
 * @Author gyf
 * @Date 2022/5/24
 **/
public class ClassUtils {
	/**
	 * 获取线程上下文类加载器
	 * ContextClassLoader的作用
	 *   破坏Java类加载机制，使程序可以逆向使用类加载器
	 *   其有get、set方法，内部维护了一个private ClassLoader contextClassLoader
	 * ContextClassLoader的实际使用
	 *   JDBC
	 *   tomcat：为了防止内部的webapp相互能够访问到，所以不应该使用appClassLoader加载
	 * @return ClassLoader
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader classLoader = null;
		classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = ClassUtils.class.getClassLoader();
		}
		return classLoader;
	}
}
