package site_mapper.creators.clazz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.class_file.ClassFile;
import file.class_package.PackageSetter;
import site_mapper.creators.imports.ImportMatcher;
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
	private ImportMatcher impMatcher;
	
	private ClassMaker classMaker;
	
	public ClassMakerDirector(
		ElementClass elementClass, PackageHierarchy ph, 
		PackageSetter packageSetter, ImportMatcher impMatcher) {
		
		this.elementClass = elementClass;
		this.packageHierarchy = ph;
		this.packageSetter = packageSetter;
		this.impMatcher = impMatcher;
	}

	public void makeClass() {
		setClassMakerType();		
		makeClassAccordingToType();		
	}
	
	private void setClassMakerType() {
		if(overWritingExisting()) {
			classMaker = new OverwriteClass(elementClass, packageHierarchy, packageSetter, impMatcher);			
		}else if (ignoringExisting()) {
			classMaker = new IgnoreClass(elementClass, packageHierarchy, packageSetter, impMatcher);			
		}else if (diffExisting()) {
			classMaker = new DiffClass(elementClass, packageHierarchy, packageSetter, impMatcher);
		}else {
			logger.error(
					String.format("Incorrect mode specified for class [%s] in module [%s]", 
							elementClass.getClassName(), elementClass.getModuleName())			
			);			
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
	
	private void makeClassAccordingToType() {
		if(classMaker != null) {
			writeCreationModeToLog(classMaker.getCreationModeStr());
			classFile = classMaker.makeClassFile();
		}
	}
	
}
