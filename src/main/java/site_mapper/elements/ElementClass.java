/**
 * 
 */
package site_mapper.elements;

import java.util.List;

import site_mapper.creators.component_writer.ComponentWriter;
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
	String getLibrary();
	
	MenuItemType getMenuItemType();
	List<Container> getAllContainers();

	SiteMapInfo getSiteMapInfo();
	ComponentWriter getComponentWriter();
	boolean hasControlList();
	
	Container getHeaderContainer();	
	Container getBodyContainer();	
	Container getFooterContainer();
	
}
