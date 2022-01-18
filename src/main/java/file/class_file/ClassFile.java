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
import file.imports.ImportList;
import site_mapper.creators.ComponentWriter;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.SiteMapInfo;

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
	private final ImportList imports;
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
		return String.format(
				"%s\n\n%s\n\n%s\n%s\n%s\n}", 
				inPackage, 
				imports.toString(), 
				getComment(), 
				getDeclaration(), 
				getClassBody());
//		return 
//				inPackage +
//				imports.toString() +
//				getComment() +
//				getDeclaration() +
//				getClassBody() + 
//				"\n}";		
	}
	
	private String getComment() {
		return (comment != null) ? comment.toString() : "/* COMMENT NOT FOUND */\n";
	}
	private String getDeclaration() {
		return (declaration != null) ? declaration.toString() : "/* CLASS DECLARATION NOT FOUND */\n";
	}
	private String getClassBody() {
		return (classBody != null) ? classBody.toString() : "/* CLASS BODY NOT FOUND */\n";
	}
	
	public abstract static class Builder {
		protected ClassPackage inPackage;
		protected ImportList imports;
		protected Comment comment;
		protected ClassDeclaration declaration;
		protected ClassBody classBody;
		
		public abstract ClassFile build();			
	}
	
	public static class ExistingClassFileBuilder extends Builder {
		public ExistingClassFileBuilder(
				ExistingClassPackage inPackage, 
				ImportList imports, 
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
		private ElementClass clazz;
		private SiteMapInfo info;
		
		public NewClassFileBuilder(ElementClass clazz) {
			this.clazz = clazz;
			this.componentWriter = clazz.getMenuItemType().getAttributes().getComponentWriter();
			this.info = clazz.getSiteMapInfo();
			
			setInPackage();
			setImports();
			setComment();
			setDeclaration();
			setClassBody();
		}

		private void setInPackage() {
			super.inPackage = new NewClassPackage(clazz.getPackage());
		}

		private void setImports() {
			super.imports = new ImportList(componentWriter.getImportNames()); 
		}

		private void setComment() {
			super.comment = new NewComment(info);
		}

		private void setDeclaration() {
			super.declaration = 
				new ClassDeclaration
					.NewDeclaration()
					.setDeclarationClazz(clazz)
					.build();
		}

		private void setClassBody() {
//			ClassBody classBody = new ;
//			super.classBody = classBody;
		}

		@Override
		public ClassFile build() {
			return new ClassFile(this);
		}
	}
}
