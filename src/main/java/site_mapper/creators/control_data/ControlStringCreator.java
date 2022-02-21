/**
 * 
 */
package site_mapper.creators.control_data;

import site_mapper.jaxb.pom.Element;
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
public class ControlStringCreator {
	private Element element;
	
	public ControlStringCreator(Element element) {	
		this.element = element;
	}
	
	private String getInitial() {
		return "\t\tControlGetter ";
	}
	private String getName() {
		return StringUtils.camelCase(element.getElementName());
	}
	private String getElementName() {
		return StringUtils.pascalCase(element.getElementName());
	}
	private String getLocator() {
		return element.getLocator().toString();
	}
	public String getDeclaration() {		
		return 
			String.format(
					"%s%s =\n\t\t\tnew ControlGetterButton(\"%s\", coreData, %s);",
					getInitial(), getName(), 
					getElementName(), getLocator());
	}

}
