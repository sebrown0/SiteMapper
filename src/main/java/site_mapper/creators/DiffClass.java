/**
 * 
 */
package site_mapper.creators;

import org.apache.logging.log4j.LogManager;

import file.class_file.ClassFile;
import file.class_package.PackageSetter;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DiffClass extends OverwriteClass implements ClassMaker {
	public DiffClass(ElementClass elementClass, PackageHierarchy packageHierarchy, PackageSetter packageSetter) {
		super(elementClass, packageHierarchy, packageSetter);
	}

	@Override //ClassMaker
	public String getCreationModeStr() {
		return String.format(
				"Checking existing class [%s] in module [%s]. Mode is[DiffExisting]", 
				elementClass.getClassName(), 
				elementClass.getModuleName());
	}
	
	@Override //ClassMaker
	public ClassFile makeClassFile() {
		LogManager.getLogger(DiffClass.class).error("** DiffExisting is not implemented **");
		return null;
	}	

}
