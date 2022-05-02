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
public class ControlTypeComboSelectOnly extends ControlType {

	public ControlTypeComboSelectOnly(ElementCreation element) {
		super(element, "cso");
	}
	
	@Override
	public String getControlDataString() {		
		return 
				String.format(
						"\t\tControlGetter %s =" +
						"\n\t\t\tnew ControlGetterComboSelectOnly(" +
						"\n\t\t\t\t\"%s\", coreData" +
						"\n\t\t\t\t%s," +
						"\n\t\t\t\tBy.className(\"select2-results\"));", 
						nameWithAcronym, nameWithAcronym, getLocators());
	}

}
