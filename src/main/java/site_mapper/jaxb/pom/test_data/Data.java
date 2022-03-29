/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Data", namespace="TestData")
public class Data {
	@XmlElement(name="item", namespace = "TestData")
	List<TestDataItem> testDataList;
}
