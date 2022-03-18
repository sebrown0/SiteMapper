/**
 * 
 */
package site_mapper.creators.navigation;

import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add an item to a nav creator.
 */
public interface NavElementAdder {
	NavElementAdder setModuleName(String modName);
	NavElementAdder setMenuName(String menuName);
	NavElementAdder setPackageHierarchy(PackageHierarchy ph);
	void addElement(String itemName);
	void writeNavClass();	
}
