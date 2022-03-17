/**
 * 
 */
package app.xml_content;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import file.class_package.PackageSetter;
import site_mapper.creators.PackageMaker;
import site_mapper.jaxb.pom.Menu;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author Brown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ModuleMapper {
	public static void mapModules(
		Module module, PackageSetter packageSetter, 
		PackageHierarchy ph, final SiteMapInfo siteMap) {
		
		String modName = module.getName();
		PackageMaker.makeWithPackageInfo(siteMap, ph.reset(modName));
  	
  	LogManager
  		.getLogger(ModuleMapper.class)
  		.info("Found module [" + modName + "]. Attempting to map menus");
  	
  	List<Menu> menus = module.getMenus(); 
		if(menus != null) {
			MenuMapper menuMapper = new MenuMapper(packageSetter, siteMap, ph, modName);
			menus.forEach(m -> menuMapper.mapMenu(m) );	
		}  	
	}
}
