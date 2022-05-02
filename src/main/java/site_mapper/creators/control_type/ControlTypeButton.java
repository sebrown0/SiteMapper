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
public class ControlTypeButton extends ControlType {

	public ControlTypeButton(ElementCreation element) {
		super(element, "btn");
	}
	
	@Override
	public String getControlDataString() {		
		return 
				String.format(
						"\t\tControlGetter %s =\n\t\t\tnew ControlGetterButton(\"%s\", coreData%s, this);",
						nameWithAcronym, nameWithAcronym, getLocators());
	}

}
