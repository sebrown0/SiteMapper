package site_mapper.creators.clazz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.class_file.ClassFile;
import file.class_package.PackageSetter;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Create/Overwrite class file.
 * 
 */
public class ClassMakerDirector {
	private ElementClass elementClass;
	private PackageHierarchy packageHierarchy;
	@SuppressWarnings("unused")
	private ClassFile classFile;
	private Logger logger = LogManager.getLogger(ClassMakerDirector.class);
	private PackageSetter packageSetter;
	
	public ClassMakerDirector(ElementClass elementClass, PackageHierarchy ph, PackageSetter packageSetter) {
		this.elementClass = elementClass;
		this.packageHierarchy = ph;
		this.packageSetter = packageSetter;
	}

	public void makeClass() {
		ClassMaker classMaker = null;
		
		if(overWritingExisting()) {
			classMaker = new OverwriteClass(elementClass, packageHierarchy, packageSetter);			
		}else if (ignoringExisting()) {
			classMaker = new IgnoreClass(elementClass, packageHierarchy, packageSetter);			
		}else if (diffExisting()) {
			classMaker = new DiffClass(elementClass, packageHierarchy, packageSetter);
		}else {
			logger.error(
					String.format("Incorrect mode specified for class [%s] in module [%s]", 
							elementClass.getClassName(), elementClass.getModuleName())
			);			
		}		
		
		if(classMaker != null) {
			writeCreationModeToLog(classMaker.getCreationModeStr());
			classFile = classMaker.makeClassFile();
		}
	}
	
	private boolean ignoringExisting() {
		return elementClass.getSiteMapInfo().isIgnoringExisting();
	}
	private boolean overWritingExisting() {
		return elementClass.getSiteMapInfo().isOverwritingExisting();
	}
	private boolean diffExisting() {
		return elementClass.getSiteMapInfo().isDiffExisting();
	}
	
	private void writeCreationModeToLog(String msg) {
		logger.info(msg);			
	}
	
}
