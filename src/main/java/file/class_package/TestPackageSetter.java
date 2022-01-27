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
 * Set the class file's package for a test class.
 * 
 */
public class TestPackageSetter implements PackageSetter{
		
	@Override
	public String getPackage(ElementClass clazz) {
		return clazz.getPackage();
	}

}
