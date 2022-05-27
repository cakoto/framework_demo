package demo.springframework.beans.factory;

/**
 * 在beanfactory基础上添加了获取Parent beanfactory的方法
 * 由bean工厂实现的子接口，可以是层次结构的一部分。
 *
 * @ClassName HierarchicalBeanFactory
 * @Description 层次结构
 * @Author gyf
 * @Date 2022/5/24
 **/
public interface HierarchicalBeanFactory extends BeanFactory{
}
