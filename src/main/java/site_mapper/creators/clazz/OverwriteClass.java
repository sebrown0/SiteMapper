/**
 * 
 */
package site_mapper.creators.clazz;

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
public class OverwriteClass implements ClassMaker {
	protected ClassFile classFile;
	protected ElementClass elementClass;
	protected PackageHierarchy packageHierarchy;
	protected PackageSetter packageSetter;

	public OverwriteClass(ElementClass elementClass, PackageHierarchy packageHierarchy, PackageSetter packageSetter) {
		this.elementClass = elementClass;
		this.packageHierarchy = packageHierarchy;
		this.packageSetter = packageSetter;
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
				.NewClassFileBuilder(elementClass, packageSetter)
				.build();
	}
	
	protected void writeClassFile() {
		ClassWriter writer = new ClassWriter(classFile, packageHierarchy);
		writer.writeClass();
	}

}
