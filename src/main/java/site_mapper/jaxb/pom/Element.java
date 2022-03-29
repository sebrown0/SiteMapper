/**
 * 
 */
package site_mapper.jaxb.pom;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.control_data.ControlStringGetter;
import site_mapper.creators.control_data.ElementControl;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Element", namespace="Container")
public class Element implements ElementCreation, ElementControl {		
	@XmlElement(name="Details", namespace="ElementType")
	private ElementDetails details;
	@XmlElement(name="Locator", namespace="ElementType")
	private List<Locator> locator;
	@XmlElement(name="ToolTip", namespace="ElementType")
	private ElementToolTip toolTip;		
	@XmlElement(name="Function", namespace="ElementType")
	private ElementFunction elementFunction;
	@XmlElement(name="TestDataIn", namespace="ElementType")
	private TestDataIn testDataIn;	
	@XmlElement(name="TestDataOut", namespace="ElementType")	
	private TestDataOut testDataOut;		
		
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
	public Element setelementFunction(ElementFunction elementFunction) {
		this.elementFunction = elementFunction.setName(getElementName());
		return this;
	}	
	public List<Locator> getLocator() {		
		return locator;
	}
	
	@Override //ElementDetails
	public Element setTestDataIn(TestDataIn data) {
		this.testDataIn = data;
		return this;
	}
	@Override //ElementDetails
	public Element setTestDataOut(TestDataOut data) {
		this.testDataOut = data;
		return this;
	}
	@Override //ElementDetails
	public TestDataIn getTestDataIn() {
		return testDataIn;
	}	
	@Override //ElementDetails
	public TestDataOut getTestDataOut() {
		return testDataOut;
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
	
	public ElementFunction getElementFunction() {
		if(elementFunction != null) {
			return 
				elementFunction
					.setName(details.getElementName())
					.setType("element")
					.setSubtype(details.getElementType());	
		}else {
			return null;
		}		
	}
	
	@Override //ElementControl
	public String getElementAsControlGetter() {
		return new ControlStringGetter(this).getString();
	}
	
	@Override
	public String toString() {
		return String.format("Element [details=%s, locator=%s, toolTip=%s, elementFunction=%s]", details, locator, toolTip,
				elementFunction);
	}
	public ElementDetails getDetails() {
		return details;
	}	
				
}
