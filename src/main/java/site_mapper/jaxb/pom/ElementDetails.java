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
@XmlRootElement(name="Details")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementDetails {
	@XmlAttribute(name="type")
	private String type;
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="text")
	private String text;
	@XmlAttribute(name="fafa")
	private String fafa;
	
	public ElementDetails setType(String type) {
		this.type = type;
		return this;
	}
	public ElementDetails setName(String name) {
		this.name = name;
		return this;
	}
	public ElementDetails setText(String text) {
		this.text = text;
		return this;
	}
	public ElementDetails setFafa(String fafa) {
		this.fafa = fafa;
		return this;
	}
	
	public String getElementName() {
		return name;
	}
	public String getText() {
		return text;
	}
	public String getFafa() {
		return fafa;
	}
	public String getElementType() {
		return type;
	}
}
