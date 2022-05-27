package demo.springframework.test.bean;

import demo.springframework.beans.factory.DisposableBean;
import demo.springframework.beans.factory.InitializingBean;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/23
 **/
public class UserService implements InitializingBean, DisposableBean {
	private String name;
	private String uId;
	private UserDao userDao;
	private String location;
	private String company;

	public UserService(){}

	public void queryUserInfo() {
		System.out.println("查询用户信息：" + userDao.queryUserName(uId));
		System.out.println(toString());
	}
	public UserService(String name) {
		this.name = name;
	}

	public void queryInfo() {
		System.out.println("查询用户信息:"+name);
	}

	public void setLocation(String s) {
		this.location = s;
	}

	@Override
	public String toString() {
		return "" + name + uId + location + company;
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("执行：UserService.destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("执行：UserService.afterPropertiesSet");
	}
}
