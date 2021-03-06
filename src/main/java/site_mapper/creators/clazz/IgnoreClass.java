/**
 * 
 */
package site_mapper.creators.clazz;

import org.apache.logging.log4j.LogManager;

import file.class_file.ClassFile;
import file.class_package.PackageSetter;
import file.helpers.ExistingFileDetails;
import file.helpers.FileFinder;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class IgnoreClass extends OverwriteClass implements ClassMaker {
	
	public IgnoreClass(
		ElementClass elementClass, PackageHierarchy packageHierarchy, 
		PackageSetter packageSetter, ImportMatcher impMatcher) {
		super(elementClass, packageHierarchy, packageSetter, impMatcher);
	}

	@Override //ClassMaker
	public String getCreationModeStr() {
		return String.format(
				"Checking existing class [%s] in module [%s]. Mode is[IgnoreExisting]", 
				elementClass.getClassName(), 
				elementClass.getModuleName());
	}
	
	@Override //ClassMaker
	public ClassFile makeClassFile() {
		ExistingFileDetails fileDetails = new ExistingFileDetails(elementClass, packageHierarchy);
		boolean fileExists = FileFinder.fileExists(fileDetails);
		if(fileExists) {
			LogManager.getLogger(IgnoreClass.class).info("Ignoring file [" + fileDetails.toString() + "]");	
		}else {
			setClassFile();
			writeClassFile();			
		}
		return classFile;
	}	

}
