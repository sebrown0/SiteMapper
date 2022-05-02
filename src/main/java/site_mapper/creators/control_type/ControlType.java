/**
 * 
 */
package site_mapper.creators.control_type;

import site_mapper.elements.ElementCreation;
import utils.text_utils.StringUtils;

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
 * 3. If the control requires any imports other than those
 * 		already added for the POM. Controls that fall into 
 * 		this category should implement RequiresImports.
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

	public abstract String getControlDataString();
		
	private void setNameAndAcronym() {
		nameWithAcronym = 
				acronym + StringUtils.capitaliseFirstChar(element.getElementName());
	}

	public String getNameWithAcronym() {
		return nameWithAcronym;
	}
	
	protected String getLocators() {
		return element.getLocators();
	}
	
}
