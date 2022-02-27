/**
 * 
 */
package site_mapper.jaxb.pom;

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
@XmlRootElement(name="text")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataText extends TestData {
	@XmlAttribute
	private String value;

	public String getValue() {
		return value;
	}
	
}
