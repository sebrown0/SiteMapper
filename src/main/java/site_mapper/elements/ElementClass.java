/**
 * 
 */
package site_mapper.elements;

import java.util.List;

import site_mapper.creators.ComponentWriter;
import site_mapper.jaxb.menu_items.MenuItemType;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.ElementFunction;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add getNodeAsSiteMapElement().
 * @version 1.2
 * 	Add getParentPackage().
 * @version 1.3
 * 	Add getModuleName().
 * @version 1.4
 * 	Add getSiteMap().
 * @since 1.0
 */
public interface ElementClass {
	String getParentPackage();
	String getPackage();
	String getClassName();	
	String getModuleName();
	String getTypeName();
	MenuItemType getMenuItemType();
	List<Element> getElements();
	List<ElementFunction> getElementFunctions();
	SiteMapInfo getSiteMapInfo();
	ComponentWriter getComponentWriter();
	boolean hasControlList();
}
