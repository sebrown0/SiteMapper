package site_mapper.creators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	@SuppressWarnings("unused")
	private Logger logger = LogManager.getLogger(ClassMaker.class);
		
	public ClassMaker(ElementClass elementClass, PackageHierarchy ph) {
		this.elementClass = elementClass;
		this.packageHierarchy = ph;
	}

	public void makeClass() {
		if(overWritingExisting()) {
			setClassFile();
			writeClassFile();			
		}else {
			System.out.println("NOT OVER WRITTING - NOT IMPLEMENTED"); // TODO - remove or log			
		}
		
	}
	
	private boolean overWritingExisting() {
		return elementClass.getSiteMapInfo().isOverwritingExisting();
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
