package demo.springframework.beans.factory.support;

import demo.springframework.beans.BeanException;
import demo.springframework.beans.factory.DisposableBean;
import demo.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName DefaultSingletonBeanRegistry
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/22
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
	private final Map<String, Object> singletonObjects = new HashMap<>();
	private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	protected void addSingleton(String beanName, Object bean) {
		singletonObjects.put(beanName, bean);
	}

	public void registerDisposableBean(String beanName, DisposableBean bean) {
		disposableBeanMap.put(beanName, bean);
	}

	/**
	 * 在所有结束后进行单例对象的回收
	 */
	public void destroySingletons() {
		Set<String> keySet = disposableBeanMap.keySet();
		/*
		// Note:循环删除map可能会抛currentxxx的异常
		Object[] disposableBeanNames = keySet.toArray();

		for (int i = disposableBeanNames.length-1;i >= 0;i--) {
			Object beanName = disposableBeanNames[i];

			DisposableBean bean = disposableBeanMap.remove(beanName);
			try {
				bean.destroy();
			} catch (Exception e) {
				throw new BeanException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
			}
		}*/
		Iterator<Map.Entry<String, DisposableBean>>it = disposableBeanMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, DisposableBean> entry = it.next();
			String beanName = entry.getKey();
			DisposableBean bean = entry.getValue();
			try {
				bean.destroy();
			} catch (Exception e) {
				throw new BeanException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
			}
			it.remove();
		}
	}
}
