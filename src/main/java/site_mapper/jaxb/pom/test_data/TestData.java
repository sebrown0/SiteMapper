/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="TestData", namespace="ElementType")
public class TestData {
	@XmlElementWrapper(name="In", namespace="ElementType")
	@XmlElement(name="item", namespace="TestData")	
	private List<TestDataItem> testDataIn;
	
	@XmlElementWrapper(name="Out", namespace="ElementType")
	@XmlElement(name="item", namespace="TestData")	
	private List<TestDataItem> testDataOut;

	public List<TestDataItem> getTestDataIn() {
		return testDataIn;
	}

	public List<TestDataItem> getTestDataOut() {
		return testDataOut;
	}

	public TestData setTestDataIn(List<TestDataItem> testDataIn) {
		this.testDataIn = testDataIn;
		return this;
	}

	public TestData setTestDataOut(List<TestDataItem> testDataOut) {
		this.testDataOut = testDataOut;
		return this;
	}
	
}
