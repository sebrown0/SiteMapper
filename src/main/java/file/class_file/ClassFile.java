/**
 * 
 */
package file.class_file;

import static file.existing.ExistingFileScanner.commentTest;
import static file.existing.ExistingFileScanner.declarationTest;
import static file.existing.ExistingFileScanner.importTest;
import static file.existing.ExistingFileScanner.packageTest;

import java.util.Scanner;

import file.class_package.ClassPackage;
import file.class_package.ExistingClassPackage;
import file.class_package.NewClassPackage;
import file.comment.Comment;
import file.comment.ExistingComment;
import file.comment.NewComment;
import file.helpers.LineMapper;
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

	private ClassFile(ClassBuilder builder) {
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
				getPackageStr(), 
				getImportStr(), 
				getCommentStr(), 
				getDeclarationStr(), 
				getClassBodyStr());		
	}
	public String getPackageStr() {
		return (inPackage != null) ? inPackage.toString() : "/* PACKAGE NOT FOUND */\n";
	}
	public String getImportStr() {
		return (imports != null) ? imports.toString() : "/* NO IMPORTS NOT FOUND */\n";
	}
	public String getCommentStr() {
		return (comment != null) ? comment.toString() : "/* COMMENT NOT FOUND */\n";
	}
	public String getDeclarationStr() {
		return (declaration != null) ? declaration.toString() : "/* CLASS DECLARATION NOT FOUND */\n";
	}
	public String getClassBodyStr() {
		return (classBody != null) ? classBody.toString() : "/* CLASS BODY NOT FOUND */\n";
	}
	
	public ClassPackage getPackage() {
		return inPackage;
	}
	public ImportList getImport() {
		return imports;
	}
	public Comment getComment() {
		return comment;
	}
	public ClassDeclaration getDeclaration() {
		return declaration;
	}
	public ClassBody getClassBody() {
		return classBody;
	}
		
	public abstract static class ClassBuilder {
		protected ClassPackage inPackage;
		protected ImportList imports;
		protected Comment comment;
		protected ClassDeclaration declaration;
		protected ClassBody classBody;
		
		public abstract ClassFile build();

//		public void setInPackage(ClassPackage inPackage) {
//			this.inPackage = inPackage;
//		}
//
//		public void setImports(ImportList imports) {
//			this.imports = imports;
//		}
//
//		public void setComment(Comment comment) {
//			this.comment = comment;
//		}
//
//		public void setDeclaration(ClassDeclaration declaration) {
//			this.declaration = declaration;
//		}
//
//		public void setClassBody(ClassBody classBody) {
//			this.classBody = classBody;
//		}			
	}
	
	public static class ExistingClassFileBuilder extends ClassBuilder {
		private Scanner scanner;
				
		public ExistingClassFileBuilder(Scanner scanner) {
			this.scanner = scanner;
		}

		public void setInPackage() {
			LineMapper
				.findByFirstWord(scanner, packageTest)
				.ifPresent(p -> 
					super.inPackage = new ExistingClassPackage(p));			
		}

		public void setImports() {
			ImportList imports = new ImportList();
			LineMapper.mapLineToList(scanner, imports.getImports(), importTest);
			super.imports = imports;
		}

		public void setComment() {
			ExistingComment comment = new ExistingComment();		
			LineMapper.mapLineToList(scanner, comment.getLines(), commentTest);
			this.comment = comment;
		}

		public void setDeclaration() {
			LineMapper.findByFirstWord(scanner, declarationTest).ifPresent(d -> {
				super.declaration =	
						new ClassDeclaration.ExistingDeclaration().setDeclarationString(d).build();				
			});			
		}

		public void setClassBody(ClassBody classBody) {
			this.classBody = classBody;
		}		

		@Override
		public ClassFile build() {
			return new ClassFile(this);
		}
	}
	
	public static class NewClassFileBuilder extends ClassBuilder {
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
