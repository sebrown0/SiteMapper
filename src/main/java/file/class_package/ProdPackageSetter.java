/**
 * 
 */
package file.class_package;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Set the class file's package for a production class.
 */
public class ProdPackageSetter implements PackageSetter{
	private SiteMapInfo info;
	
	public ProdPackageSetter(SiteMapInfo info) {
		this.info = info;
	}

	@Override
	public String getPackage(ElementClass clazz) {
		String s = 
				info.getParentPackage() + "." + 
				clazz.getModuleName() + "." + 
				clazz.getParentPackage() + "." + 
				clazz.getPackage();
		
		return s;
	}
	
}
