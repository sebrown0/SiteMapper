package site_mapper.jaxb.pom;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.NotImplemented;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.PackageMaker;
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
 * The tests for each menu item, i.e. EmployeeDetails are
 * returned from MenuItem as a Map<String, List<DynamicTest>>.
 * 
 */
@XmlRootElement(name="Menu")
public class Menu {
	@XmlAttribute(name="name")
	private String name;	
	@XmlAttribute(name="package")
	private String packageName;	
  @XmlElement(name="MenuItem")
  private List<MenuItem> menuItems;
    
  private Logger logger = LogManager.getLogger(Menu.class);
  
  public Menu getMenuContainers(SiteMapInfo siteMap, PackageHierarchy ph, String moduleName) {
		PackageMaker.makeWithPackageInfo(siteMap, ph.addCurrent(name));		
		logger.info("Found menu [" + name + "]. Attempting to map menu items");
		if(menuItems != null) {
			menuItems.forEach(item -> {
				try {
					item.createPoms(siteMap, ph, moduleName);
				} catch (NotImplemented e) {
					logger.error("Error creating POM class file [" + e + "]");
				}
			});	
		}		
		return this;
	}

	
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	public String getName() {
		return name;
	}  
	public String getPackageName() {
		return packageName;
	}
}
