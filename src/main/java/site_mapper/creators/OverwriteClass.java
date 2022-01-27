/**
 * 
 */
package site_mapper.creators;

import file.class_file.ClassFile;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class OverwriteClass implements ClassMaker {
	protected ClassFile classFile;
	protected ElementClass elementClass;
	protected PackageHierarchy packageHierarchy;
	
	public OverwriteClass(ElementClass elementClass, PackageHierarchy packageHierarchy) {
		this.elementClass = elementClass;
		this.packageHierarchy = packageHierarchy;
	}

	@Override //ClassMaker
	public String getCreationModeStr() {
		return String.format(
				"Creating/overwriting class [%s] in module [%s]. Mode is[OverwriteExisting]", 
				elementClass.getClassName(), 
				elementClass.getModuleName());				
	}
	
	@Override //ClassMaker
	public ClassFile makeClassFile() {
		setClassFile();
		writeClassFile();
		
		return classFile;
	}
	
	protected void setClassFile() {
		classFile = 
			new ClassFile
				.NewClassFileBuilder(elementClass)
				.build();
	}
	
	protected void writeClassFile() {
		ClassWriter writer = new ClassWriter(classFile, packageHierarchy);
		writer.writeClass();
	}

}
