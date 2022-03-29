/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

@XmlRootElement(name="Out", namespace="ElementType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataOut {
	
	@XmlElement(name="Data", namespace="TestData")
	Data data;
	
}
