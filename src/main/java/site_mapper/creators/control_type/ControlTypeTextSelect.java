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
public class ControlTypeTextSelect extends ControlType {

	public ControlTypeTextSelect(ElementCreation element) {
		super(element, "tsl");
	}
	
	@Override
	public String getControlDataString() {		
		return 
				String.format(
						"\t\tControlGetter %s =\n\t\t\tnew ControlGetterTextSelect(\n\t\t\t\t\"%s\", coreData%s);",
						nameWithAcronym, nameWithAcronym, getLocators());
	}

}
