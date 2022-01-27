package site_mapper.creators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.NotImplemented;
import file.class_file.ClassFile;
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
		
	public ClassMakerDirector(ElementClass elementClass, PackageHierarchy ph) {
		this.elementClass = elementClass;
		this.packageHierarchy = ph;
	}

	public void makeClass() throws NotImplemented {
		ClassMaker classMaker = null;
		
		if(overWritingExisting()) {
			classMaker = new OverwriteClass(elementClass, packageHierarchy);			
		}else if (ignoringExisting()) {
			classMaker = new IgnoreClass(elementClass, packageHierarchy);			
		}else if (diffExisting()) {
			classMaker = new DiffClass(elementClass, packageHierarchy);
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
