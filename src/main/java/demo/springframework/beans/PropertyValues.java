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
