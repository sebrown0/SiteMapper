/**
 * 
 */
package site_mapper.jaxb.pom;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
//@XmlRootElement(name="TestData")
//@XmlType
//@XmlSeeAlso({TestDataList.class, TestDataText.class})
public abstract class TestData {
	public abstract String getValue();
//
//	public String getValue() {
//		return "NOOOOO";
//	}
	
//	abstract String getValue();
	
//  @XmlElementWrapper(name="In")
//  @XmlElements({
//      @XmlElement(name="Text", type=ElementTestDataText.class),
//      @XmlElement(name="List", type=ElementTestDataList.class)
//  })  
//  public List<TestDataType> list;
	
}
