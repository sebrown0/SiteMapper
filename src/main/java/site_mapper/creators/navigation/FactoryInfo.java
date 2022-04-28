/**
 * 
 */
package site_mapper.creators.navigation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface FactoryInfo {
	// root from PackageHierarchy
	String getRoot(); 
	
	// i.e. library.object_models.modules (from SiteMapInfo.ParentPackage)
	String getParentPackage();
	
	// Set by NavElementAdder.
	String getModule(); 
}
