/**
 * 
 */
package site_mapper.jaxb.pom;

import org.apache.logging.log4j.LogManager;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.elements.TestData;
import site_mapper.elements.TestDataGetter;
import site_mapper.elements.ElementDataList;
import site_mapper.elements.ElementDataText;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="TestData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementTestData implements TestDataGetter {	
	@XmlAttribute(name="text")
	private String text;
	@XmlAttribute(name="list")
	private String list;
	
	public String getText() {
		return text;
	}
	public String getList(){
		return list;
	}
	
	@Override //TestDataGetter
	public TestData getTestData() {
		TestData data = null;
		if(text != null) {
			data = new ElementDataText().setData(this);
		}else if(list != null) {
			data = new ElementDataList().setData(this);
		}else {
			LogManager
				.getLogger(ElementTestData.class)
				.info("No test data found");
		}
		return data;  
	}
}
