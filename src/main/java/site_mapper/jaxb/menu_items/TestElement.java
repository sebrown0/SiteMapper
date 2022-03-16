/**
 * 
 */
package site_mapper.jaxb.menu_items;

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
	TestElement setSiteMapInfo(SiteMapInfo info);
	TestElement setTypeAttributes (TypeAttributes getTypeAttributes);
	TestElement setName(String str); 
}
