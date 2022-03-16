/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

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

@XmlRootElement(name="TestDataOut", namespace="TestDataOut")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataOut {
	@XmlElements(value={
		@XmlElement(name="Text", namespace="TestDataOut", type=TestDataText.class),
		@XmlElement(name="List", namespace="TestDataOut", type=TestDataList.class)
	})
	private TestData testData;
	
	public TestData getTestData() {
		return testData;
	}
	public void setTestData(TestData testData) {
		this.testData = testData;
	}
}
