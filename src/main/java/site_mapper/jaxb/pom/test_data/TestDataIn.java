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

@XmlRootElement(name="TestDataIn", namespace="ElementType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataIn {
	@XmlElement(name="Data", namespace="ElementType")
	Data data;
	
//	@XmlAttribute(name="insertWith")
//	private String insertWith;
//	
//	@XmlElements(value={
//		@XmlElement(name="Text", namespace="TestDataIn", type=TestDataText.class),
//		@XmlElement(name="List", namespace="TestDataIn", type=TestDataList.class)
//	})
//	private TestData testData;
//		
//	public TestData getTestData() {
//		return testData;
//	}
//	public TestDataIn setTestData(TestData testData) {
//		this.testData = testData;
//		return this;
//	}
//	public String getInsertWith() {
//		return insertWith;
//	}
//	public TestDataIn setInsertWith(String insertWith) {
//		this.insertWith = insertWith;
//		return this;
//	}
}
