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
	private PackageSetter packageSetter;
	private SiteMapInfo siteMapInfo;
	private PackageHierarchy ph;
	private String moduleName;
	
	private static final Logger LOGGER = LogManager.getLogger(MenuMapper.class);

	public MenuMapper(PackageSetter packageSetter, SiteMapInfo siteMapInfo, PackageHierarchy ph, String moduleName) {
		this.packageSetter = packageSetter;
		this.siteMapInfo = siteMapInfo;
		this.ph = ph;
		this.moduleName = moduleName;
	}

	public void mapMenu(Menu menu) {  	
		logMsg(menu);		
		createPackageForMenu(menu);		
		mapItemsForThisMenu(menu);		
	}

	private void logMsg(Menu menu) {
		String menuName = menu.getName();
		LOGGER.info("Found menu [" + menuName + "]. Attempting to map menu items");
	}
	
	private void createPackageForMenu(Menu menu) {		
  	String packageName = menu.getPackageName();  	
  	ph.reset(moduleName).addCurrent(packageName);
		PackageMaker.makeWithPackageInfo(siteMapInfo, ph);
	}
	
  private void mapItemsForThisMenu(Menu menu) {
  	List<MenuItem> menuItems = menu.getMenuItems();
  	
  	if(menuItems != null) {
			menuItems.forEach(item -> {
				item.setSiteMapInfo(siteMapInfo);				
				item.setTestModuleName(moduleName);
				item.setTestMenuName(menu.getPackageName());				
				new MenuItemMapper(packageSetter, item, ph).createPoms();
			});	
		}		
  }
}