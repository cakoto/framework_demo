package demo.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName HttpFileResource
 * @Description 通过URL读取文件信息
 * @Author gyf
 * @Date 2022/5/24
 **/
public class HttpFileResource implements Resource{

	private final URL url;

	public HttpFileResource(URL url) {
		Assert.notNull(url, "URL must not be null");
		this.url = url;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		URLConnection connection = this.url.openConnection();
		return connection.getInputStream();
	}
}
