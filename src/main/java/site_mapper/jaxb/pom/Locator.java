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
public class Locator {
	@XmlAttribute(name="by")
	private String by;
	@XmlAttribute(name="text")
	private String locator;	
	
	public String getLocatorStr() {
		return null;
	}
	public Locator setBy(String by) {
		this.by = by;
		return this;
	}
	public Locator setLocator(String locator) {
		this.locator = locator;
		return this;
	}
	public String getBy() {
		return by;
	}
	public String getLocator() {
		return locator;
	}

}
