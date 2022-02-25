/**
 * 
 */
package site_mapper.jaxb.pom;

import java.io.Serializable;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="TestData")
@XmlSeeAlso({TestDataIn.class})
public abstract class TestData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	abstract String getValue();
	
//  @XmlElementWrapper(name="In")
//  @XmlElements({
//      @XmlElement(name="Text", type=ElementTestDataText.class),
//      @XmlElement(name="List", type=ElementTestDataList.class)
//  })  
//  public List<TestDataType> list;
	
}
