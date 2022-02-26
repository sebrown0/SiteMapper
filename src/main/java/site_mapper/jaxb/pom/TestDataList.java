/**
 * 
 */
package site_mapper.jaxb.pom;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author Brown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="list")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataList extends TestData{
	@XmlAttribute
	private String value;

	public String getValue() {
		return value;
	}
		
}
