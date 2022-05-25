package demo.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.URL;

/**
 * @ClassName DefaultResourceLoader
 * @Description 定义获取资源接口 传递location即可
 * @Author gyf
 * @Date 2022/5/24
 **/
public class DefaultResourceLoader implements ResourceLoader{

	/**
	 * 有三种输入：classpath、url、filepath
	 * classpath就是类文件的路径，这个项目中的一个路径
	 * filepath就是本地文件的路径
	 * url就是网络中文件的路径
	 *
	 * @param location 地址
	 * @return 输入流
	 */
	@Override
	public Resource getResource(String location) {
		Assert.notNull(location, "Location must not be null");
		if (location.startsWith(CLASSPATH_URL_PREFIX)) {
			return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
		} else {
			try {
				URL url = new URL(location);
				return new HttpFileResource(url);
			} catch (Exception e) {
				return new FileResource(location);
			}
		}
	}
}
