package demo.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/23
 **/
public class UserDao {
	private static Map<String, String> hashMap = new HashMap<>();

	static {
		hashMap.put("10001", "阿鸟");
		hashMap.put("10002", "鸟哥");
		hashMap.put("10003", "小鸟");
	}

	public String queryUserName(String uId) {
		return hashMap.get(uId);
	}
}
