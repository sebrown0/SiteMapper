/**
 * 
 */
package site_mapper.jaxb.pom;

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
@XmlRootElement(name="Locator")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementLocator {
	@XmlAttribute(name="by")
	private String by;
	@XmlAttribute(name="text")
	private String locator;	
	
	public void setBy(String by) {
		this.by = by;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	public String getBy() {
		return by;
	}
	public String getLocator() {
		return locator;
	}

}
