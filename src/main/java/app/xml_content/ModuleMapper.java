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
			Module module, PackageSetter packageSetter, PackageHierarchy ph, final SiteMapInfo siteMap) {
		
		String name = module.getName();
		PackageMaker.makeWithPackageInfo(siteMap, ph.reset().addCurrent(name));
  	
  	LogManager
  		.getLogger(ModuleMapper.class)
  		.info("Found module [" + name + "]. Attempting to map menus");
  	
  	List<Menu> menus = module.getMenus(); 
		if(menus != null) {			
			menus.forEach(m -> {								 					
				MenuMapper.mapMenus(m, packageSetter, siteMap, ph, name);
	  	});	
		}  	
	}
}
