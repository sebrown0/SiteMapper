/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="TestData", namespace="ElementType")
public class TestData {

	@XmlElement(name="In", namespace="ElementType")	
	private TestDataIn testDataIn;
	
	@XmlElement(name="Out", namespace="ElementType")	
	private TestDataOut testDataOut;

	public TestDataIn getTestDataIn() {
		return testDataIn;
	}

	public TestDataOut getTestDataOut() {
		return testDataOut;
	}
	
}
