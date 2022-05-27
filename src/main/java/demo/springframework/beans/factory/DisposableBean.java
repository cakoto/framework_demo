package demo.springframework.beans.factory;

/**
 * 回调接口
 */
public interface DisposableBean {

	void destroy() throws Exception;

}
