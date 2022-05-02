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
public class ControlTypeTextOut extends ControlType {

	public ControlTypeTextOut(ElementCreation element) {
		super(element, "txo");
	}
	
	@Override
	public String getControlDataString() {		
		return 
				String.format(
						"\t\tControlGetter %s =\n\t\t\tnew ControlGetterTextOut(\"%s\", coreData%s, this);",
						nameWithAcronym, nameWithAcronym, getLocators());
	}

}
