/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.List;

import site_mapper.elements.Element;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface TestElement {
	TestElement setTestPackage(String str);
	TestElement setTestClassName(String str);
	TestElement setTestItemType(MenuItemType menuItemType);
//	void setMenuItemType(MenuItemType type);
	TestElement setElements(List<Element> elements);
	TestElement setSiteMapInfo(SiteMapInfo info);
}
