package demo.springframework.core.io;

import cn.hutool.core.lang.Assert;
import demo.springframework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName ClassPathResource
 * @Description 通过ClassLoader读取ClassPath下的文件信息
 * @Author gyf
 * @Date 2022/5/24
 **/
public class ClassPathResource implements Resource{

	private final String path;

	private final ClassLoader classLoader;

	public ClassPathResource(String path) {
		this(path, (ClassLoader) null);
	}

	public ClassPathResource(String path, ClassLoader classLoader) {
		Assert.notNull(path, "Path must not be null");
		this.path = path;
		this.classLoader = (classLoader == null) ? ClassUtils.getDefaultClassLoader(): classLoader;
	}

	/**
	 * 通过ClassLoader读取ClassPath下的文件信息
	 * 由classLoader.getResourceAsStream(path)完成
	 * @return InputStream
	 * @throws IOException
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		InputStream inputStream = classLoader.getResourceAsStream(path);
		if (inputStream == null) {
			throw new FileNotFoundException(
					this.path + " cannot be opened because it does not exist"
			);
		}
		return inputStream;
	}
}
