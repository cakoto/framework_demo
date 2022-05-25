package demo.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName FileResource
 * @Description 通过指定文件路径读取文件信息
 * @Author gyf
 * @Date 2022/5/24
 **/
public class FileResource implements Resource{

	private final File file;

	private final String path;

	public FileResource(File file) {
		this.file = file;
		this.path = file.getPath();
	}

	public FileResource(String path) {
		this.file = new File(path);
		this.path = path;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}

	public final String getPath() {
		return this.path;
	}
}
