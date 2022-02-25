/**
 * 
 */
package site_mapper.jaxb.pom;

import java.util.Arrays;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="List")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementTestDataList extends TestDataType {
	@XmlAttribute(name="name")
	public String name;
	@XmlAttribute(name="value")
	public String value;	
	
	public List<String> getList(){
		List<String> res = null;
		if(value != null) {
			res = Arrays.asList(value.split(","));
		}
		return res;
	}
	
}
