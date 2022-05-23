package demo.springframework.beans;

/**
 * @ClassName BeanException
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public class BeanException extends RuntimeException{

	public BeanException(String msg) {
		super(msg);
	}

	public BeanException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
