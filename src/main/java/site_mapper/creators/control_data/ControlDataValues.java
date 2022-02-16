/**
 * 
 */
package site_mapper.creators.control_data;

import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for the values required by ControlDataStringFactory.
 */
public class ControlDataValues {
	private String controlName;
	private String controlTypeName;
	private String byValue;
	private String byType;
	
	public ControlDataValues(ElementCreation e) {
		this.controlName = e.getElementName();
		this.controlTypeName = e.getElementType();
		this.byValue = e.getByLocatorValue();
		this.byType = e.getByLocatorType();
	}
	
	public String getControlName() {
		return controlName;
	}
	public String getControlTypeName() {
		return controlTypeName;
	}
	public String getByValue() {
		return byValue;
	}
	public String getByType() {
		return byType;
	}
	
}
