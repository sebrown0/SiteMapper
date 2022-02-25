/**
 * 
 */
package site_mapper.jaxb.pom;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
//@XmlRootElement(name="TestDataIn")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name="TestDataIn")
public class TestDataIn extends TestData {	
	private static final long serialVersionUID = 1L;
	
	@XmlAttribute(name="value")
	private String value;

	public String getValue() {
		return value;
	}
	
//	private TestDataType testDataIn;	
//	private String text;
//	
//	@XmlAttribute(name="text")
//	public void setText(String text) {
//		
//	}
	
//	@XmlElements({
//		@XmlElement(name="Text", type=ElementTestDataText.class),
//		@XmlElement(name="List", type=ElementTestDataList.class)
//	})
//	public void setTestDataIn(TestDataType testDataIn) {
//		this.testDataIn = testDataIn;
//	}
//
//	public TestDataType getTestDataIn() {
//		return testDataIn;
//	}
	
//	@Override
//	public String getText() {
//		return text;
//	}
		
}
