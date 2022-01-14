/**
 * 
 */
package file.class_file;

import file.class_package.ClassPackage;
import file.comment.Comment;
import file.imports.Import;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ClassFile {
	private ClassPackage inPackage;
	private Import imports;
	private Comment comment;
	private ClassDeclaration declaration;
	private ClassBody classBody;

	public ClassFile setInPackage(ClassPackage inPackage) {
		this.inPackage = inPackage;
		return this;
	}	
	public ClassFile setImport(Import imprt) {
		this.imports = imprt;
		return this;
	}
	public ClassFile setComment(Comment comment) {
		this.comment = comment;
		return this;
	}	
	public ClassFile setDeclaration(ClassDeclaration declaration) {
		this.declaration = declaration;
		return this;
	}
	public ClassFile setClassBody(ClassBody classBody) {
		this.classBody = classBody;
		return this;
	}
	
	@Override 
	public String toString() {
		String res = 
				inPackage +
				imports.toString() +
				comment.toString() +
				declaration.toString() +
				classBody.toString() + 
				"\n}";
		
		return res;
	}
	
	
}
