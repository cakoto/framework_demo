package demo.springframework.context;

import demo.springframework.beans.factory.ListableBeanFactory;

/**
 * 为应用程序提供配置的中心接口。
 *
 * @ClassName ApplicationContext
 * @Description 继承了BeanFactory和ListableBeanFactory的方法，中心方法
 * @Author gyf
 * @Date 2022/5/25
 **/
public interface ApplicationContext extends ListableBeanFactory {
}
