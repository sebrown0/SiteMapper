/**
 * 
 */
package app.xml_content;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import file.class_package.PackageSetter;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.package_maker.PackageMaker;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;
import site_mapper.jaxb.pom.menu.MenuType;

/**
 * @author Brown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ModuleMapper {
	public static void mapModules(
		Module module, PackageSetter packageSetter, 
		PackageHierarchy ph, final SiteMapInfo siteMap, ImportMatcher impMatcher) {
		
		String modName = module.getName();
		PackageMaker.makeWithPackageInfo(siteMap, ph.reset(modName));
  	
  	LogManager
  		.getLogger(ModuleMapper.class)
  		.info("Found module [" + modName + "]. Attempting to map menus");
  	
  	List<MenuType> menus = module.getMenus(); 
		if(menus != null) {
			MenuMapper menuMapper = new MenuMapper(packageSetter, siteMap, ph, modName, impMatcher);
			menus.stream()
				.filter(m -> m != null)
				.forEach(m -> menuMapper.mapMenu(m) );	
		}  	
	}
}
