/**
 * 
 */
package site_mapper.jaxb.pom;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Tab")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZZZ_Tab {
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="locator")
	private String locator;	
	@XmlAttribute(name="fafa")
	private String fafa;
	
	@XmlElementWrapper(name="Elements")
	@XmlElement(name="Element")
	private List<Element> elements;

	public String getName() {
		return name;
	}
	public String getLocator() {
		return locator;
	}
	public String getFafa() {
		return fafa;
	}
	public List<Element> getElements() {
		return elements;
	}
	
	@Override
	public String toString() {
		return String.format("Tab [name=%s, locator=%s, fafa=%s, elements=%s]", name, locator, fafa, elements);
	}	
		
}
