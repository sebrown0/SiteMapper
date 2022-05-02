/**
 * 
 */
package site_mapper.creators.control_type;

import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlTypeDropDownCombo extends ControlType {

	public ControlTypeDropDownCombo(ElementCreation element) {
		super(element, "cdd");
	}
	
	@Override
	public String getControlDataString() {		
		return 
				String.format(
						"\t\tControlGetter %s =" +
						"\n\t\t\tnew ControlGetterDropDownCombo(" +
						"\n\t\t\t\t\"%s\", coreData" +
						"\n\t\t\t\t%s);", 
						nameWithAcronym, nameWithAcronym, getLocators());
	}

}
