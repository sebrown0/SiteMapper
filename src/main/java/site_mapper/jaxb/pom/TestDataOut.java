/**
 * 
 */
package site_mapper.jaxb.pom;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

@XmlRootElement(name="TestDataOut")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataOut {
	@XmlElements(value={
		@XmlElement(name="text", type=TestDataText.class),
		@XmlElement(name="list", type=TestDataList.class)
	})
	private TestData value;
	
	public TestData getValue() {
		return value;
	}

}
