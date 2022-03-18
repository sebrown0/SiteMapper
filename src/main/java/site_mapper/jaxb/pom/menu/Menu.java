package site_mapper.jaxb.pom.menu;

import java.util.List;

import file.helpers.Formatter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
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
  @XmlElements(value = { 
    @XmlElement(name="Left", 
    						namespace="Menu",
                type=LeftMenu.class),
    @XmlElement(name="TopRight",
    						namespace="Menu",
                type=TopRight.class)
  })
  private MenuType menuType;
    
	public List<MenuItem> getMenuItems() {
		return menuType.getMenuItems();
	}
	public String getName() {
		return (menuType != null) ? menuType.getName() : null;
	}  
	public String getPackageName() {
		return (menuType != null) ? menuType.getPackageName() : null;
	}

	public MenuType getMenuType() {
		return menuType;
	}
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	
	@Override
	public String toString() {
		return 
			String.format("Menu [name=%s, packageName=%s, menuItems=[%s]]", 
			getName(), getPackageName(), Formatter.getAsCommaSeparatedList(menuType.getMenuItems()));
	}

}
