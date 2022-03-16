/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Text", namespace="Text")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataText extends TestData {
	@XmlAttribute
	private String value;

	public String getValue() {
		return value;
	}
	public TestData setValue(String value) {
		this.value = value;
		return this;
	}
}
