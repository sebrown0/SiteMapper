/**
 * 
 */
package site_mapper.creators.control_data;

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
	private String getName() {
		return StringUtils.camelCase(element.getElementName());
	}
	private String getType() {
		return StringUtils.pascalCase(element.getElementType());
	}
	private String getElementName() {
		return StringUtils.pascalCase(element.getElementName());
	}
	private String getLocator() {
		Locator loc = element.getLocator(); 
		return (loc != null) ? ", " + loc.toString() : "";
	}
	public String getString() {		
		return 
			String.format(
					"%s%s =\n\t\t\tnew ControlGetter%s(\"%s\", coreData%s);",
					getInitial(), getName(), getType(),
					getElementName(), getLocator());
	}

}
