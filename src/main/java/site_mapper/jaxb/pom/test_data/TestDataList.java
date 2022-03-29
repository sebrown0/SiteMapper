/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="List")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataList extends ZZZ_TestData {
	@XmlAttribute
	private String value;

	public String getValue() {
		return value;
	}
	public ZZZ_TestData setValue(String value) {
		this.value = value;
		return this;
	}
	
	public List<String> getAsList(){
		return StringUtils.getListFromString(value, ",");
	}
		
}
