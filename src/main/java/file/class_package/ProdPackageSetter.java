/**
 * 
 */
package file.class_package;

import site_mapper.elements.ElementClass;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Set the class file's package for a production class.
 */
public class ProdPackageSetter implements PackageSetter{
	@Override
	public String getPackage(ElementClass clazz) {
		String s = 
				getParentPackage(clazz) + "." +
				clazz.getModuleName() + "." +
				clazz.getParentPackage() + "." +
				clazz.getPackage();
		
		return s;
	}
	
	private String getParentPackage(ElementClass clazz) {
		String s = clazz.getSiteMapInfo().getParentPackage().replace("/", "."); 
		return s;
	}
}
