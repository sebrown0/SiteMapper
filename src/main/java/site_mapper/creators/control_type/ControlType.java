/**
 * 
 */
package site_mapper.creators.control_type;

import site_mapper.elements.ElementCreation;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the details that are specific to the type of control.
 * 1. Its name with appropriate acronym, i.e. btnNext.
 * 2. The control string that's used in buildMyControls() 
 * 		in the control's POM class.
 */
public abstract class ControlType {
	private String acronym;
	
	protected ElementCreation element;
	protected String nameWithAcronym;
	
	public ControlType(ElementCreation element, String acronym) {		
		this.element = element;
		this.acronym = acronym;
		
		setNameAndAcronym();
	}
		
	private void setNameAndAcronym() {
		nameWithAcronym = 
				acronym + StringUtils.capitaliseFirstChar(element.getElementName());
	}

	public abstract String getControlDataString();

	public String getNameWithAcronym() {
		return nameWithAcronym;
	}
	
	protected String getLocators() {
		return element.getLocators();
	}
	
}
