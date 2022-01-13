/**
 * 
 */
package file;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ClassFile {
	private String inPackage;
	private List<String> imports = new ArrayList<>();
	private List<String> comments = new ArrayList<>();
	private ClassBody classBody;
	
	public ClassFile addImport(String imprt) {
		imports.add(imprt);
		return this;
	}
	public ClassFile addComment(String comment) {
		comments.add(comment);
		return this;
	}
	
	public ClassFile setInPackage(String inPackage) {
		this.inPackage = inPackage;
		return this;
	}	
	public ClassFile setClassBody(ClassBody classBody) {
		this.classBody = classBody;
		return this;
	}
	
	public String getInPackage() {
		return inPackage;
	}
	public List<String> getImports() {
		return imports;
	}
	public List<String> getComments() {
		return comments;
	}
	public ClassBody getClassBody() {
		return classBody;
	}
	
	
}
