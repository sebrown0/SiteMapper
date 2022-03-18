package site_mapper.jaxb.pom.menu;

import java.util.List;

import file.helpers.Formatter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.menu_items.MenuItemTop;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Get MenuItems' tests from the map returned from MenuItem.
 * @since 1.0
 * 
 * Menu found in a module.
 * 
 */
@XmlRootElement(name="Menu", namespace="Menu")
public class TopRight extends MenuType {
  private static final String NAME="TopRight";
	private static final String PACKAGE_NAME="top_right_nav";
	
	@XmlElement(name="MenuItem", namespace="TopRightMenu")
  private List<MenuItemTop> menuItems;  
	
	public List<MenuItemTop> getMenuItems() {
		return menuItems;
	}
	public String getName() {
		return NAME;
	}  
	public String getPackageName() {
		return PACKAGE_NAME;
	}
	
	@Override
	public String toString() {
		return String.format("Menu [name=%s, packageName=%s, menuItems=[%s]]", NAME, PACKAGE_NAME, Formatter.getAsCommaSeparatedList(menuItems));
	}

}
