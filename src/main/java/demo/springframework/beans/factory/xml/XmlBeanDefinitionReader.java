package demo.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import demo.springframework.beans.BeanException;
import demo.springframework.beans.PropertyValue;
import demo.springframework.beans.factory.config.BeanDefinition;
import demo.springframework.beans.factory.config.BeanReference;
import demo.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import demo.springframework.beans.factory.support.BeanDefinitionRegistry;
import demo.springframework.core.io.Resource;
import demo.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName XmlBeanDefinitionReader
 * @Description 解析XML文件并将属性进行注入
 * @Author gyf
 * @Date 2022/5/24
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
	}

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		super(registry, resourceLoader);
	}

	@Override
	public void loadBeanDefinitions(Resource resource) throws BeanException {
		try {
			InputStream inputStream = resource.getInputStream();
			doLoadBeanDefinitions(inputStream);
		} catch (ClassNotFoundException | IOException e) {
			throw new BeanException("IOException parsing XML document from "+ e);
		}
	}

	@Override
	public void loadBeanDefinitions(Resource... resources) throws BeanException {
		for (Resource resource: resources) {
			loadBeanDefinitions(resource);
		}
	}

	@Override
	public void loadBeanDefinitions(String location) throws BeanException {
		ResourceLoader resourceLoader = getResourceLoader();
		Resource resource = resourceLoader.getResource(location);
		loadBeanDefinitions(resource);
	}

	@Override
	public void loadBeanDefinitions(String... locations) throws BeanException {
		for (String location: locations) {
			loadBeanDefinitions(location);
		}
	}

	/**
	 * 读取xml和element的解析
	 * 通过对元素的解析，以此获取 Bean 配置以及配置中的 id、name、class、value、ref 信息。
	 * 读取出来的配置信息创建成BeanDefinition和PropertyValue
	 * 最终把完整的 Bean 定义内容注册到 Bean 容器
	 *
	 * jdom解析方式
	 *
	 * @param inputStream 输入流
	 * @throws ClassNotFoundException 类异常
	 */
	protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
		Document document = XmlUtil.readXML(inputStream);
		Element root = document.getDocumentElement();
		NodeList childNodes = root.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			// 判断元素
			if (!(childNodes.item(i) instanceof Element)) continue;
			// 判断对象
			if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

			// 解析标签
			Element bean = (Element) childNodes.item(i);
			String id = bean.getAttribute("id");
			String name = bean.getAttribute("name");
			String className = bean.getAttribute("class");
			// 获取 Class，方便获取类中的名称
			Class<?> clazz = Class.forName(className);
			// 优先级 id > name
			String beanName = StrUtil.isNotEmpty(id) ? id : name;
			if (StrUtil.isEmpty(beanName)) {
				beanName = StrUtil.lowerFirst(clazz.getSimpleName());
			}

			// 定义Bean
			BeanDefinition beanDefinition = new BeanDefinition(clazz);
			// 读取属性并填充
			for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
				if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
				if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
				// 解析标签：property
				Element property = (Element) bean.getChildNodes().item(j);
				String attrName = property.getAttribute("name");
				String attrValue = property.getAttribute("value");
				String attrRef = property.getAttribute("ref");
				// 获取属性值：引入对象、值对象
				Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
				// 创建属性信息
				PropertyValue propertyValue = new PropertyValue(attrName, value);
				beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
			}
			if (getRegistry().containsBeanDefinition(beanName)) {
				throw new BeanException("Duplicate beanName[" + beanName + "] is not allowed");
			}
			// 注册 BeanDefinition
			getRegistry().registerBeanDefinition(beanName, beanDefinition);
		}
	}
}
