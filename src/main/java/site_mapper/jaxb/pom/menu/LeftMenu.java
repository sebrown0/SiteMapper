package site_mapper.jaxb.pom.menu;

import java.util.List;

import file.helpers.Formatter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.navigation.NavElementAdder;
import site_mapper.creators.navigation.left_menu.LeftMenuElementCreator;
import site_mapper.jaxb.menu_items.MenuItem;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Get MenuItems' tests from the map returned from MenuItem.
 * @since 1.0
 * 
 * Left menu type, found in a module.
 * 
 */
@XmlRootElement(name="Left", namespace="Menus")
public class LeftMenu extends MenuType {
	private static final String NAME="Left";
  private static final String PACKAGE_NAME="left_menu";
  
  @XmlElement(name="MenuItem", namespace="Menu")
  private List<MenuItem> menuItems;
  
  public List<MenuItem> getMenuItems() {
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
		return 
			String.format("Menu [name=%s, packageName=%s, menuItems=[%s]]", 
				NAME, PACKAGE_NAME, Formatter.getAsCommaSeparatedList(menuItems));
	}

	@Override
	public NavElementAdder getNavCreator(ImportMatcher impMatcher) {
		return new LeftMenuElementCreator(impMatcher);
	}

}
