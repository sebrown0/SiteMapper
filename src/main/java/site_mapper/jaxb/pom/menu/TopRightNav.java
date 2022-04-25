package site_mapper.jaxb.pom.menu;

import java.util.List;

import file.helpers.Formatter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.navigation.NavBarElementSetter;
import site_mapper.creators.navigation.NavElementAdder;
import site_mapper.jaxb.menu_items.MenuItem;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Get MenuItems' tests from the map returned from MenuItem.
 * @since 1.0
 * 
 * Top right menu, found in a module.
 * 
 */
@XmlRootElement(name="TopRight", namespace="Menu")
public class TopRightNav extends MenuType {
  private static final String NAME="TopRight";
	private static final String PACKAGE_NAME="top_right_nav";
	
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
	public NavElementAdder getNavCreator(ImportMatcher impMatcher) {
		return new NavBarElementSetter(impMatcher);
	}
	
	@Override
	public String toString() {
		return 
			String.format("Menu [name=%s, packageName=%s, menuItems=[%s]]", 
				NAME, PACKAGE_NAME, Formatter.getAsCommaSeparatedList(menuItems));
	}

}
