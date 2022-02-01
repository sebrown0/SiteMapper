/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.List;

import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Use to set an element for testing.
 */
public interface TestElement {
	TestElement setTestPackage(String str);
	TestElement setTestModuleName(String str);
	TestElement setTestMenuName(String str);
	TestElement setTestClassName(String str);
	TestElement setTestItemType(MenuItemType menuItemType);
	TestElement setElements(List<Element> elements);
	TestElement setSiteMapInfo(SiteMapInfo info);
}
