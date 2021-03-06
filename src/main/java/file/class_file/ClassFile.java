/**
 * 
 */
package file.class_file;

import static file.existing.ExistingFileScanner.COMMENT_TEST;
import static file.existing.ExistingFileScanner.DECLARATION_TEST;
import static file.existing.ExistingFileScanner.IMPORT_SUPPLIER;
import static file.existing.ExistingFileScanner.IMPORT_TEST;
import static file.existing.ExistingFileScanner.PACKAGE_TEST;
import static file.existing.ExistingFileScanner.STRING_SUPPLIER;

import java.util.Scanner;

import file.class_file.body.ClassBody;
import file.class_package.ClassPackage;
import file.class_package.ExistingClassPackage;
import file.class_package.NewClassPackage;
import file.class_package.PackageSetter;
import file.comment.Comment;
import file.comment.ExistingComment;
import file.comment.NewComment;
import file.helpers.LineMapper;
import file.imports.Import;
import file.imports.ImportList;
import site_mapper.creators.component_writer.ComponentWriter;
import site_mapper.creators.component_writer.ComponentWriterSetter;
import site_mapper.creators.imports.ImportMatcher;
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

	public ClassFile(ClassBuilder builder) {
		this.inPackage = builder.inPackage;
		this.imports = builder.imports;
		this.comment = builder.comment;
		this.declaration = builder.declaration;
		this.classBody = builder.classBody;
	}
		
	@Override 
	public String toString() {
		return String.format(
				"%s\n\n%s\n%s%s\n%s\n%s\n}", 
				getPackageStr(), 
				getImportStr(), 
				getCommentStr(), 
				getSuppressUnusedStr(),
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
		return (comment != null) ? comment.toString() + "\n": "/* COMMENT NOT FOUND */\n";
	}
	public String getSuppressUnusedStr() {
		return "@SuppressWarnings(\"unused\")";
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
		
	public abstract static class ClassBuilder implements ImportAppender {
		protected ClassPackage inPackage;
		protected ImportList imports;
		protected Comment comment;
		protected ClassDeclaration declaration;
		protected ClassBody classBody;
		
		protected abstract void setInPackage();
		protected abstract void setImports();
		protected abstract void setComment();
		protected abstract void setDeclaration();		
		protected abstract ClassFile build();	
	}
	
	public static class ExistingClassFileBuilder extends ClassBuilder {
		private Scanner scanner;
				
		public ExistingClassFileBuilder(Scanner scanner) {
			this.scanner = scanner;
		}
		@Override
		public void setInPackage() {
			LineMapper
				.findByFirstWord(scanner, PACKAGE_TEST)
				.ifPresent(p -> 
					super.inPackage = new ExistingClassPackage(p));			
		}
		@Override
		public void setImports() {
			ImportList imports = new ImportList();
			LineMapper.mapLinesToList(scanner, imports.getImports(), IMPORT_TEST, IMPORT_SUPPLIER);
			super.imports = imports;
		}
		@Override
		public void setComment() {
			ExistingComment comment = new ExistingComment();
			LineMapper.mapLinesToList(scanner, comment.getLines(), COMMENT_TEST, STRING_SUPPLIER);
			this.comment = comment;
		}
		@Override
		public void setDeclaration() {
			LineMapper.findByFirstWord(scanner, DECLARATION_TEST).ifPresent(d -> {
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
		@Override
		public void appendImport(Import imp) {
			super.imports.addImport(imp);
		}
		
	}
	
	public static class NewClassFileBuilder extends ClassBuilder {
		private ComponentWriter componentWriter;
		private ElementClass clazz;
		private SiteMapInfo info;
		private PackageSetter packageSetter;
		private ImportMatcher impMatcher;
		
		public NewClassFileBuilder(
			ElementClass clazz, PackageSetter packageSetter, ImportMatcher impMatcher) {
			
			this.clazz = clazz;
			this.info = clazz.getSiteMapInfo();
			this.packageSetter = packageSetter;
			this.impMatcher = impMatcher;
			
			setComponentWriter();			
			setInPackage();
			setImports();
			setComment();
			setDeclaration();
			setClassBody();
		}
		
		//Set the component writer with necessary objects.
		private void setComponentWriter() {
			componentWriter = clazz.getComponentWriter();
			((ComponentWriterSetter) componentWriter).setSiteMapInfo(info);
			((ComponentWriterSetter) componentWriter).setElementClass(clazz);
			((ComponentWriterSetter) componentWriter).setImportMatcher(impMatcher);
		}
		
		@Override
		protected void setInPackage() {			
			super.inPackage = new NewClassPackage(packageSetter.getPackage(clazz));
		}
		@Override
		protected void setImports() {
			super.imports = new ImportList(componentWriter.getImportNames()); 
		}
		@Override
		protected void setComment() {
			super.comment = new NewComment(info);
		}
		@Override
		protected void setDeclaration() {
			super.declaration = 
				new ClassDeclaration
					.NewDeclaration()
					.setDeclarationClazz(clazz)
					.build();
		}

		private void setClassBody() {			
			ClassBody classBody = 
					new ClassBody
						.NewClassBody(componentWriter, clazz, info, this, impMatcher)
						.build();
			super.classBody = classBody;
		}

		@Override
		public ClassFile build() {
			return new ClassFile(this);
		}

		@Override
		public void appendImport(Import imp) {
			super.imports.addImport(imp);			
		}
	}

	public String getClassName() {
		return declaration.getName();
	}
	
}
