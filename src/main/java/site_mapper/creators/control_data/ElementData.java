/**
 * 
 */
package site_mapper.creators.control_data;

import site_mapper.jaxb.pom.Element;
import utils.StringUtils;

/**
 * @author Brown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ElementData {
	private Element element;
//	private String declaration;
	
	public ElementData(Element element) {	
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
		return element.getByLocatorType();
	}
	public String getDeclaration() {		
		return 
			String.format(
					"%s%s =\n\t\t\tnew ControlGetterButton(\"%s\", coreData, %s",
			getInitial(),getName(), getElementName());
			
			//"new ControlGetterButton(\"Save\", coreData, By.cssSelector(\"button[name='SAVE']\"));";
	}
//	ControlGetterGroup tabs = 
//			new ControlGetterTabs("Tabs", coreData, By.cssSelector("ul[class='nav nav-tabs']"))
//				.addControls(Arrays.asList(salaryDetails));
}
