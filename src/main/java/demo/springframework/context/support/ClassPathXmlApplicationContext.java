package demo.springframework.context.support;

import demo.springframework.beans.BeanException;

/**
 * @ClassName ClassPathXmlApplicationContext
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/25
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

	private String[] configLocations;

	public ClassPathXmlApplicationContext() {
	}

	public ClassPathXmlApplicationContext(String configLocations) throws BeanException {
		this(new String[]{configLocations});
	}

	public ClassPathXmlApplicationContext(String[] configLocations) throws BeanException {
		this.configLocations = configLocations;
		refresh();
	}

	@Override
	protected String[] getConfigLocations() {
		return configLocations;
	}


}
