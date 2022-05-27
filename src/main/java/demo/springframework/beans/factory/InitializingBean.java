package demo.springframework.beans.factory;

public interface InitializingBean {

	/**
	 * Bean属性填充完成后调用
	 */
	void afterPropertiesSet() throws Exception;

}
