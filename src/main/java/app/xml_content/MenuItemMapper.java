/**
 * 
 */
package app.xml_content;

import file.class_package.PackageSetter;
import site_mapper.creators.clazz.ClassMakerDirector;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.package_maker.PackageMaker;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class MenuItemMapper {
	private String packageName;	
	private SiteMapInfo siteMapInfo;
	private PackageSetter packageSetter;
	private MenuItem menuItem;
	private PackageHierarchy ph;
	private ImportMatcher impMatcher;
	
	public MenuItemMapper(PackageSetter packageSetter, MenuItem menuItem, PackageHierarchy ph, ImportMatcher impMatcher) {		
		this.siteMapInfo = menuItem.getSiteMapInfo();
		this.ph = ph;
		this.packageSetter = packageSetter;
		this.menuItem = menuItem;
		this.packageName = menuItem.getPackage();
		this.impMatcher = impMatcher;
	}

	public void createPoms() {						
		boolean createPackage = createPackageForClassIfNecessary();
		createClass();
		removeThisClassPackageFromHierarchy(createPackage);		
	}
	
	private void createClass() {
		ClassMakerDirector cm = new ClassMakerDirector(menuItem, ph, packageSetter, impMatcher);
		cm.makeClass();
	}
	
	private boolean createPackageForClassIfNecessary() {
		if(packageName != null && packageName.length() > 0) {
			PackageMaker.makeWithPackageInfo(siteMapInfo, ph.addCurrent(packageName));
			return true;			
		}
		return false;
	}
	
	private void removeThisClassPackageFromHierarchy(boolean packageWasAddedToHierarchy) {
		if(packageWasAddedToHierarchy) {
			ph.removeCurrent();
		}		
	}
	
}
