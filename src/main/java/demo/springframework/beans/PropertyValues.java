package demo.springframework.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName PropertyValues
 * @Description TODO
 * @Author gyf
 * @Date 2022/5/23
 **/
public class PropertyValues {

	/**
	 * 由于这个是一个列表，后面进行修改的会放置在后面，在填充属性值的时候是从前往后遍历的
	 * 所有能够覆盖原先存在的值，达到正确赋值的目的
	 */
	private final List<PropertyValue> propertyValueList = new ArrayList<>();

	public void addPropertyValue(PropertyValue propertyValue) {
		this.propertyValueList.add(propertyValue);
	}

	public PropertyValue[] getPropertyValues() {
		return propertyValueList.toArray(new PropertyValue[0]);
	}

	public PropertyValue getPropertyValue(String propertyName) {
		for (PropertyValue propertyValue: propertyValueList) {
			if (Objects.equals(propertyValue.getName(), propertyName)) {
				return propertyValue;
			}
		}
		return null;
	}
}
