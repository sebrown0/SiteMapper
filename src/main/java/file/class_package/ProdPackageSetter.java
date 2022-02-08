/**
 * 
 */
package file.class_package;

import site_mapper.elements.ElementClass;
import utils.StringUtils;

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
				StringUtils.replaceFwdSlashes(clazz.getSiteMapInfo().getParentPackage(), ".") + "." +
				clazz.getModuleName() + "." +
				clazz.getParentPackage() +
				getPackageIfExists(clazz);
		
		return s;
	}
	
	private String getPackageIfExists(ElementClass clazz) {
		String pck = clazz.getPackage();
		if(pck != null && !pck.equals("")) {
			pck = pck.toLowerCase();
			return "." + pck;	
		}else {
			return "";
		}		
	}
	
}
