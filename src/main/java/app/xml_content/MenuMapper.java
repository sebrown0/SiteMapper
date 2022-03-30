/**
 * 
 */
package app.xml_content;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.class_package.PackageSetter;
import site_mapper.creators.navigation.NavElementAdder;
import site_mapper.creators.package_maker.PackageMaker;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;
import site_mapper.jaxb.pom.menu.MenuType;

/**
 * @author Brown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class MenuMapper {	
	private PackageSetter packageSetter;
	private SiteMapInfo siteMapInfo;
	private PackageHierarchy ph;
	private String moduleName;
	private NavElementAdder elementAdder;
	
	private static final Logger LOGGER = LogManager.getLogger(MenuMapper.class);

	public MenuMapper(PackageSetter packageSetter, SiteMapInfo siteMapInfo, PackageHierarchy ph, String moduleName) {
		this.packageSetter = packageSetter;
		this.siteMapInfo = siteMapInfo;
		this.ph = ph;
		this.moduleName = moduleName;
	}

	public void mapMenu(MenuType menu) {  	
		logMsg(menu);		
		createPackageForMenu(menu);		
		mapItemsForThisMenu(menu);		
	}

	private void logMsg(MenuType menu) {
		String menuName = menu.getName();
		LOGGER.info("Found menu [" + menuName + "]. Attempting to map menu items");
	}
	
	private void createPackageForMenu(MenuType menu) {		
  	String packageName = menu.getPackageName();  	
  	ph.reset(moduleName).addCurrent(packageName);
		PackageMaker.makeWithPackageInfo(siteMapInfo, ph);
	}
	
  private void mapItemsForThisMenu(MenuType menu) {
  	List<MenuItem> menuItems = menu.getMenuItems();
  	
  	if(menuItems != null) {
  		setNavCreator(menu);
			menuItems.forEach(item -> {
				addItemToNavigation(item);
				createPom(menu, item);								
			});
			writeNavigation();
		}		
  }
  
  private void setNavCreator(MenuType menu) {
  	elementAdder = 
  		menu
  			.getNavCreator()
  			.setSiteMapInfo(siteMapInfo)
  			.setPackageHierarchy(ph)
  			.setMenuName(menu.getName())
  			.setModuleName(moduleName);
  }
    
  private void addItemToNavigation(MenuItem item) {
		elementAdder.addElement(item.getClassName());
  }
  
  private void createPom(MenuType menu, MenuItem item) {
  	item.setSiteMapInfo(siteMapInfo);				
		item.setTestModuleName(moduleName);
		item.setTestMenuName(menu.getPackageName());				
		new MenuItemMapper(packageSetter, item, ph).createPoms();
  }
  
  private void writeNavigation() {
  	elementAdder.writeNavClass();
  }
}