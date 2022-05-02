/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.List;

import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.Locator;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the control string as it should be displayed
 * in the class file where the element is declared, 
 * i.e. the buildMyControls() function.
 */
public class ControlStringGetter {
	private Element element;
	
	public ControlStringGetter(Element element) {	
		this.element = element;
	}
	
	private String getInitial() {
		return "\t\tControlGetter ";
	}
	private String getControlNameWithAcronym() {
		return element.getNameWithAcronym();
	}

	private String getType() {
		return StringUtils.asPascalCase(element.getElementType());
	}

	private String getLocators() {
		String res = "";
		List<Locator> locs = element.getLocator();
		if(locs != null) {
			for(Locator loc : locs) {
				res += ", " + loc.toString();
			}
		}
		return res;
	}
	
	public String getString() {		
		String nameWithAcronym = getControlNameWithAcronym();
		/*
		 * REALLY NEED TO HAVE A CONTROL FROM THE BEGGING SO THAT
		 * 1. WE CAN GET IT'S ACRONYM
		 * 2. WE GET THE CORRECT CONTROL STRING
		 * 
		 * ONLY THE ELEMENT KNOWS ITS TYPE SO THIS WOULD
		 * HAVE TO RETURN AN OBJECT THAT DOES BOTH OF THE ABOVE,
		 * FOR EACH TYPE OF CONTROL.
		 * 
		 * SO WE WOULD NEED A FACTORY.
		 * 
		 * CAN WE LINK THE FACTORY(all relevant factories) TO XML?
		 */
		
		return 
			String.format(
					"%s%s =\n\t\t\tnew ControlGetter%s(\"%s\", coreData%s, this);",
					getInitial(), nameWithAcronym, getType(),
					nameWithAcronym, getLocators());
	}

}
