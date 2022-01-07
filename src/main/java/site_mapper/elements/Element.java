/**
 * 
 */
package site_mapper.elements;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Element")
public class Element implements ElementCreation, ElementFunction {
	@XmlAttribute(name="type")
	private String type;
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="text")
	private String text;
	@XmlAttribute(name="by")
	private String by;
	@XmlAttribute(name="locator")
	private String locator;	
	@XmlAttribute(name="fafa")
	private String fafa;
	@XmlAttribute(name="hasFunction")
	private String hasFunction;
	
	public Element setType(String type) {
		this.type = type;
		return this;
	}
	public Element setName(String name) {
		this.name = name;
		return this;
	}
	public Element setText(String text) {
		this.text = text;
		return this;
	}
	public Element setBy(String by) {
		this.by = by;
		return this;
	}
	public Element setLocator(String locator) {
		this.locator = locator;
		return this;
	}
	public Element setFafa(String fafa) {
		this.fafa = fafa;
		return this;
	}
	public Element setHasFunction(String hasFunction) {
		this.hasFunction = hasFunction;
		return this;
	}
	@Override //ElementDetails
	public String getElementName() {
		return name;
	}
	@Override //ElementDetails
	public String getText() {
		return text;
	}
	@Override //ElementDetails
	public String getFafa() {
		return fafa;
	}
	
	@Override //ElementCreation
	public String getElementType() {
		return type;
	}
	@Override //ElementCreation
	public String getByLocatorValue() {
		return locator;
	}
	@Override //ElementCreation
	public String getByLocatorType() {
		return by;
	}
	
	@Override //ElementFunction
	public boolean hasFunction() {
		return (hasFunction.equalsIgnoreCase("true")) ? true : false;
	}
	
	@Override
	public String toString() {
		return "Element [type=" + type + ", name=" + name + ", by=" + by + ", locator=" + locator + ", text=" + text
				+ ", fafa=" + fafa + ", response=" + hasFunction + "]";
	}
		
}
