/**
 * 
 */
package app.xml_content;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.class_package.PackageSetter;
import site_mapper.creators.PackageMaker;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Menu;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author Brown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class MenuMapper {
	private static final Logger logger = LogManager.getLogger(MenuMapper.class);
  
  public static void mapMenus(Menu menu, PackageSetter packageSetter, SiteMapInfo siteMapInfo, PackageHierarchy ph, String moduleName) {
  	String name = menu.getName();
  	String packageName = menu.getPackageName();
  	List<MenuItem> menuItems = menu.getMenuItems();
  	
  	ph.setMenuPackageName(packageName);
		PackageMaker.makeWithPackageInfo(siteMapInfo, ph.addCurrent(packageName));		
		
		logger.info("Found menu [" + name + "]. Attempting to map menu items");
		
		if(menuItems != null) {
			menuItems.forEach(item -> {
				item.setSiteMapInfo(siteMapInfo);
				new MenuItemMapper(packageSetter, item, ph).createPoms();
//				item.createPoms(packageSetter, siteMap, ph, moduleName);
			});	
		}		
	}
}
