/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="item", namespace="TestData")
public class TestDataItem {
	@XmlAttribute(name="id")
	String id;
	@XmlAttribute(name="value")
	String value;
	@XmlAttribute(name="insertWith")
	String insertWith;
}
