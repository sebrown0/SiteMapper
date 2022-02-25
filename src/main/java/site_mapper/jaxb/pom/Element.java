/**
 * 
 */
package site_mapper.jaxb.pom;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.control_data.ControlStringGetter;
import site_mapper.creators.control_data.ElementData;
import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Element")
public class Element implements ElementCreation, ElementData {		
	@XmlElement(name="Details")
	private ElementDetails details;
	@XmlElement(name="Locator")
	private Locator locator;
	@XmlElement(name="ToolTip")
	private ElementToolTip toolTip;
	@XmlElement(name="TestData")
	private ElementTestData testData;
	@XmlElement(name="Function")
	private ElementFunction elementFunction;
	
	public Element setType(String type) {
		if(details == null) details = new ElementDetails();
		details.setType(type);
		return this;
	}
	public Element setTestData(ElementTestData testData) {
		this.testData = testData;
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
		if(locator == null) locator = new Locator();
		this.locator.setBy(by);
		return this;
	}
	public Element setLocator(String locator) {
		if(this.locator == null) this.locator = new Locator();
		this.locator.setLocator(locator);
		return this;
	}
	public Element setLocator(Locator locator) {
		this.locator = locator;
		return this;
	}
	
	public Element setelementFunction(ElementFunction elementFunction) {
		this.elementFunction = elementFunction;
		return this;
	}
		
	public Locator getLocator() {		
		return locator;
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
	public ElementTestData setTestData() {
		return testData;
	}
	@Override //ElementDetails
	public String getText() {
		return (details != null) ? details.getText() : "";
	}
	@Override //ElementDetails
	public String getFafa() {
		return (details != null) ? details.getFafa() : "";
	}
	@Override //ElementDetails
	public ElementTestData getTestData() {
		return testData;
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
	public String getElementString() {
		return new ControlStringGetter(this).getString();
	}
	
	@Override
	public String toString() {
		return String.format("Element [details=%s, locator=%s, toolTip=%s, elementFunction=%s]", details, locator, toolTip,
				elementFunction);
	}	
				
}
