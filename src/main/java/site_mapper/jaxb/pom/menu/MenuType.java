/**
 * 
 */
package site_mapper.jaxb.pom.menu;

import java.util.List;

import site_mapper.creators.navigation.NavElementAdder;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class MenuType {
	
	public abstract String getName();
	public abstract String getPackageName();
	public abstract List<MenuItem> getMenuItems();
	public abstract NavElementAdder getNavCreator();
	
}
