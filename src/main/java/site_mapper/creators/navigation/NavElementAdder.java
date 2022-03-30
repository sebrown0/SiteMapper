/**
 * 
 */
package site_mapper.creators.navigation;

import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add an item to a nav creator.
 */
public interface NavElementAdder {
	NavElementAdder setSiteMapInfo(SiteMapInfo info);
	NavElementAdder setModuleName(String modName);
	NavElementAdder setMenuName(String menuName);
	NavElementAdder setPackageHierarchy(PackageHierarchy ph);
	void addElement(String itemName);
	void writeNavClass();	
}
