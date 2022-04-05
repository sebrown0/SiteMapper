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

@XmlRootElement(name="In", namespace="ElementType")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestDataIn {
	
	@XmlElement(name="Data", namespace="TestData")
	private Data data;

	public Data getData() {
		return data;
	}

	public TestDataIn setData(Data data) {
		this.data = data;
		return this;
	}
	
}
