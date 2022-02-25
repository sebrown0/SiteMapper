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
@XmlRootElement(name="TestData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementTestData {	
	@XmlAttribute(name="text")
	private String text;
	@XmlAttribute(name="list")
	private String list;
	
	public String getText() {
		return text;
	}
		
	public List<String> getList(){
		List<String> res = null;
		if(list != null) {
			res = Arrays.asList(list.split(","));	
		}		
		return res;
	}
}
