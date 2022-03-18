/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.List;

import site_mapper.jaxb.containers.Container;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * The details of a menu item.
 */
public abstract class MenuItemDetails {
	protected abstract String getName();
	protected abstract String getPackageName(); 	
	protected abstract String getClassName();
	protected abstract MenuItemType getMenuItemType();
	protected abstract List<Container> getItemContainers();
	
	public MenuItemDetails() {}
}
