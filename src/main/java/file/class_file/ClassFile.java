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
 * 
 * A site mapped class file with all its elements.
 * Use one of the builders to create the object.
 * 
 */
public class ClassFile {
	private final ClassPackage inPackage;
	private final Import imports;
	private final Comment comment;
	private final ClassDeclaration declaration;
	private final ClassBody classBody;

	private ClassFile(Builder builder) {
		this.inPackage = builder.inPackage;
		this.imports = builder.imports;
		this.comment = builder.comment;
		this.declaration = builder.declaration;
		this.classBody = builder.classBody;
	}
		
	@Override 
	public String toString() {
		return 
				inPackage +
				imports.toString() +
				comment.toString() +
				declaration.toString() +
				classBody.toString() + 
				"\n}";		
	}
	
	public abstract static class Builder {
		protected ClassPackage inPackage;
		protected Import imports;
		protected Comment comment;
		protected ClassDeclaration declaration;
		protected ClassBody classBody;
		
		public abstract ClassFile build();			
	}
	
	public static class ExistingClassFileBuilder extends Builder {
		public ExistingClassFileBuilder(
				ClassPackage inPackage, 
				Import imports, 
				Comment comment, 
				ClassDeclaration declaration, 
				ClassBody classBody) {
			
			this.inPackage = inPackage;
			this.imports = imports;
			this.comment = comment;
			this.declaration = declaration;
			this.classBody = classBody;
		}

		@Override
		public ClassFile build() {
			return new ClassFile(this);
		}
	}
}
