package demo.springframework.test.bean;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/23
 **/
public class UserService {
	String name;

	public UserService(String name) {
		this.name = name;
	}

	public void queryInfo() {
		System.out.println("查询用户信息:"+name);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("");
		sb.append("").append(name);
		return sb.toString();
	}
}
