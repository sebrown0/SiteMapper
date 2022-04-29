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
//		Locator loc = element.getLocator();
		if(locs != null) {
			for(Locator loc : locs) {
				res += ", " + loc.toString();
			}
		}
		return res;
//		return (loc != null) ? ", " + loc.toString() : "";
	}
	
	public String getString() {		
		String nameWithAcronym = getControlNameWithAcronym();
		return 
			String.format(
					"%s%s =\n\t\t\tnew ControlGetter%s(\"%s\", coreData%s, this);",
					getInitial(), nameWithAcronym, getType(),
					nameWithAcronym, getLocators());
	}

}
