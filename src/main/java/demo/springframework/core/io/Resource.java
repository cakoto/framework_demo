package demo.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName Resource
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/24
 **/
public interface Resource {
	InputStream getInputStream() throws IOException;
}
