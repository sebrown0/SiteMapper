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
public class ClassMaker {
	private ElementClass elementClass;
	private PackageHierarchy packageHierarchy;
	private ClassFile classFile;
	private Logger logger = LogManager.getLogger(ClassMaker.class);
		
	public ClassMaker(ElementClass elementClass, PackageHierarchy ph) {
		this.elementClass = elementClass;
		this.packageHierarchy = ph;
	}

	public void makeClass() throws NotImplemented {
		if(overWritingExisting()) {
			writeCreationModeToLog("OverwriteExisting");
			setClassFile();
			writeClassFile();			
		}else if (ignoringExisting()) {
			writeCreationModeToLog("IgnoreExisting");
			throw new NotImplemented("IgnoreExisting is not implemented");
		}else if (diffExisting()) {
			writeCreationModeToLog("DiffExisting");
			throw new NotImplemented("DiffExisting is not implemented"); 	
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
	
	private void writeCreationModeToLog(String mode) {
		logger.info(
				String.format(
						"Creating class [%s] in module [%s]. Mode is[%s]", 
						elementClass.getClassName(), 
						elementClass.getModuleName(),
						mode)
		);			
	}
	
	private void setClassFile() {
		classFile = 
				new ClassFile
					.NewClassFileBuilder(elementClass)
					.build();
	}
	private void writeClassFile() {
		ClassWriter writer = new ClassWriter(classFile, packageHierarchy);
		writer.writeClass();
	}
}
