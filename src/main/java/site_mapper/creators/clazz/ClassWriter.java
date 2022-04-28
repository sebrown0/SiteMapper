/**
 * 
 */
package site_mapper.creators.clazz;

import file.class_file.ClassFile;
import file.helpers.Formatter;
import site_mapper.jaxb.pom.PackageHierarchy;
import utils.FileWriter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Write the given class file.
 * 
 */
public class ClassWriter {
	private PackageHierarchy packageHierarchy;
	private ClassFile clazz;
		
	public ClassWriter(ClassFile clazz, PackageHierarchy packageHierarchy) {
		this.packageHierarchy = packageHierarchy;
		this.clazz = clazz;
	}

	public void writeClass() {		
		FileWriter.writeFile(clazz, getFilePath());	
	}
		
	private String getFilePath() {
		return 
				packageHierarchy.getRoot() + "/" + 
				packageHierarchy.getHierarchyFwdSlashNotation() + "/" + 
				Formatter.capitaliseFirstChar(clazz.getClassName()) + ".java";
	}
	
}
