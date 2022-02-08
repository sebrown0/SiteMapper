/**
 * 
 */
package site_mapper.jaxb.pom;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Element")
public class Element implements ElementCreation {		
	@XmlElement(name="Details")
	private ElementDetails details;
	@XmlElement(name="Locator")
	private ElementLocator locator;
	@XmlElement(name="ToolTip")
	private ElementToolTip toolTip;
	@XmlElement(name="Function")
	private ElementFunction elementFunction;
		
	public Element setType(String type) {
		if(details == null) details = new ElementDetails();
		details.setType(type);
		return this;
	}
	public Element setName(String name) {
		if(details == null) details = new ElementDetails();
		details.setName(name);
		return this;
	}
	public Element setText(String text) {
		if(details == null) details = new ElementDetails();
		details.setText(text);
		return this;
	}
	public Element setFafa(String fafa) {
		if(details == null) details = new ElementDetails();
		details.setFafa(fafa);
		return this;
	}

	public Element setBy(String by) {
		if(locator == null) locator = new ElementLocator();
		this.locator.setBy(by);
		return this;
	}
	public Element setLocator(String locator) {
		if(locator == null) this.locator = new ElementLocator();
		this.locator.setLocator(locator);
		return this;
	}
	
	public Element setelementFunction(ElementFunction elementFunction) {
		this.elementFunction = elementFunction;
		return this;
	}
		
	@Override //ElementDetails
	public String getToolTipText() {
		return (toolTip != null) ? toolTip.getText() : "";
	}
	
	@Override //ElementDetails
	public String getElementName() {
		return (details != null) ? details.getElementName() : "";
	}
	@Override //ElementDetails
	public String getText() {
		return (details != null) ? details.getText() : "";
	}
	@Override //ElementDetails
	public String getFafa() {
		return (details != null) ? details.getFafa() : "";
	}
	@Override //ElementCreation
	public String getElementType() {
		return (details != null) ? details.getElementType() : "";
	}
	@Override //ElementCreation
	public String getByLocatorValue() {
		return (locator != null) ? locator.getLocator() : "";		
	}
	@Override //ElementCreation
	public String getByLocatorType() {
		return (locator != null) ? locator.getBy() : "";		
	}
	public ElementFunction getElementFunction() {
		if(elementFunction != null) {
			return 
				elementFunction
					.setName(getElementName())
					.setType(getElementType());	
		}else {
			return null;
		}		
	}
	
	@Override
	public String toString() {
		return String.format(
				"Element [type=%s, name=%s, text=%s, by=%s, locator=%s, fafa=%s, toolTipText=%s, elementFunction=%s]", getElementType(),
				getElementName(), getText(), getByLocatorType(), locator.getLocator(), getFafa(), getToolTipText(), elementFunction);
	}
			
}
