/**
 * 
 */
package site_mapper.elements;

import java.util.List;

import site_mapper.creators.ComponentWriter;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.menu_items.MenuItemType;
import site_mapper.jaxb.menu_items.TypeAttributes;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ElementClass {
	TypeAttributes getTypeAttributes();
	String getParentPackage();
	String getPackage();
	String getName();
	String getClassName();	
	String getModuleName();
	String getTypeName();
	MenuItemType getMenuItemType();
	List<Container> getAllContainers();
	Container getHeader();
	Container getBody();
	Container getFooter();
	SiteMapInfo getSiteMapInfo();
	ComponentWriter getComponentWriter();
	boolean hasControlList();
	
}
