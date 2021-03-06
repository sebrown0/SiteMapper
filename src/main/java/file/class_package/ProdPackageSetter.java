/**
 * 
 */
package file.class_package;

import static utils.text_utils.StringUtils.replaceFwdSlashes;

import site_mapper.elements.ElementClass;
import utils.clazz.PackageNameResolver;;

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
				replaceFwdSlashes(clazz.getSiteMapInfo().getParentPackage(), ".") + "." +
				clazz.getModuleName() + "." +
				clazz.getParentPackage() + "." +
				getPackageIfExists(clazz);
		
		return s;
	}
	
	private String getPackageIfExists(ElementClass clazz) {
		return new 
			PackageNameResolver(clazz.getPackage())
				.getPackageInCorrectFormat();
	}
	
}
