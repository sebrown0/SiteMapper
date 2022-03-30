/**
 * 
 */
package site_mapper.creators.navigation;

import java.util.ArrayList;
import java.util.List;

import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class NavElement_XXX {
	private List<MenuItem> leftItems = new ArrayList<>();
	private List<MenuItem> topRightItems = new ArrayList<>();
	
	public void addLeftItem(MenuItem item) {
		leftItems.add(item);
	}
	public void addTopRightItem(MenuItem item) {
		topRightItems.add(item);
	}
	/*
	 * TODO Get better name for this and the class. 
	 */
	public void createNav() {
		/*
		 * 
		 */
	}
}
