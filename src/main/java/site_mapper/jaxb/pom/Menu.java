package site_mapper.jaxb.pom;

import java.util.List;

import file.helpers.Formatter;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.menu_items.MenuItem;

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
@XmlRootElement(name="Menu", namespace="Menus")
public class Menu {
	@XmlAttribute(name="name")
	private String name;	
	@XmlAttribute(name="package")
	private String packageName;	
  @XmlElement(name="MenuItem", namespace="Menu")
  private List<MenuItem> menuItems;
    	
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	public String getName() {
		return name;
	}  
	public String getPackageName() {
		return packageName;
	}
	
	@Override
	public String toString() {
		return String.format("Menu [name=%s, packageName=%s, menuItems=[%s]]", name, packageName, Formatter.getAsCommaSeparatedList(menuItems));
	}


}
