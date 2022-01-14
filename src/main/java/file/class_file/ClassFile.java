/**
 * 
 */
package file.class_file;

import file.class_package.ClassPackage;
import file.class_package.ExistingClassPackage;
import file.class_package.NewClassPackage;
import file.comment.Comment;
import file.comment.ExistingComment;
import file.comment.NewComment;
import file.imports.ExistingImport;
import file.imports.Import;
import file.imports.NewImport;
import site_mapper.creators.ComponentWriter;

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
				ExistingClassPackage inPackage, 
				ExistingImport imports, 
				ExistingComment comment, 
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
	
	public static class NewClassFileBuilder extends Builder {
		private ComponentWriter componentWriter;
		
		public NewClassFileBuilder(ComponentWriter componentWriter) {
			this.componentWriter = componentWriter;
		}

		public NewClassFileBuilder setInPackage(NewClassPackage inPackage) {
			super.inPackage = inPackage;
			return this;
		}

		public NewClassFileBuilder setImports(NewImport imports) {
			super.imports = imports;
			return this;
		}

		public NewClassFileBuilder setComment(NewComment comment) {
			super.comment = comment;
			return this;
		}

		public NewClassFileBuilder setDeclaration(ClassDeclaration declaration) {
			super.declaration = declaration;
			return this;
		}

		public void setClassBody(ClassBody classBody) {
			super.classBody = classBody;
		}

		@Override
		public ClassFile build() {
			return new ClassFile(this);
		}
	}
}
