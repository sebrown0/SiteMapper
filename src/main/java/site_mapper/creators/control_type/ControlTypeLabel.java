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
public class ControlTypeLabel extends ControlType {

	public ControlTypeLabel(ElementCreation element) {
		super(element, "lbl");
	}
	
	@Override
	public String getControlDataString() {		
		return 
				String.format(
						"\t\tControlGetter %s =\n\t\t\tnew ControlGetterLabel(\"%s\", coreData%s, this);",
						nameWithAcronym, nameWithAcronym, getLocators());
	}

}
