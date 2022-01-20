/**
 * 
 */
package helpers;

import java.util.Arrays;

import file.class_file.ClassBody;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_file.ClassFile.ClassBuilder;
import file.class_package.ExistingClassPackage;
import file.comment.ExistingComment;
import file.imports.ExistingImport;
import file.imports.ImportList;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestClassFileBuilder extends ClassBuilder {

	@Override
	public void setInPackage() {
		super.inPackage = new ExistingClassPackage("package a.payroll.Left.employees;");
		
	}
	@Override
	public void setImports() {
		ImportList imports = 
				new ImportList(Arrays.asList(
						new ExistingImport("import java.util.List;"),
						new ExistingImport("import control_builder.*;")
				));
		super.imports = imports;		
	}
	@Override
	public void setComment() {
		ExistingComment comment = new ExistingComment();
		comment
			.addLine("/**")
			.addLine("* Generated Class.")
			.addLine("* ----------------")
			.addLine("* Source:  C:/site_map.xml")
			.addLine("* Author:  SteveBrown")
			.addLine("* Version: 1.0.0")
			.addLine("* Created: 07/01/2022 08:53:56")
			.addLine("*/");
		super.comment = comment;
	}
	@Override
	public void setDeclaration() {
		ClassDeclaration declaration = 
				new ClassDeclaration
					.ExistingDeclaration()
					.setDeclarationString("public class EmployeeDetails extends JsPanelWithIFrame {")
					.build();
		
		super.declaration = declaration;
	}

	public void setClassBody(ClassBody classBody) {
		super.classBody = classBody;
	}
	
	@Override
	public ClassFile build() {
		this.setInPackage();
		this.setImports();
		this.setComment();
		this.setDeclaration();
		this.setClassBody(new TestClassBodyBuilder().build());
		return new ClassFile(this);
	}

}
