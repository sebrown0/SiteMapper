/**
 * 
 */
package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
import file.class_file.ClassBody;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_file.ClassFile.ClassBuilder;
import file.class_package.ExistingClassPackage;
import file.comment.ExistingComment;
import file.imports.ExistingImport;
import file.imports.Import;
import file.imports.ImportList;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestClassFileBuilder extends ClassBuilder {
	public static final String EXISTING_PACKAGE = 
						"package a.payroll.Left.employees;";
	public static final String NEW_PACKAGE = 
						"a.payroll.Left.employees;";
	public static final String PACKAGE_RESULT = 
						"package a.payroll.Left.employees;";
	
	public static final String ANNO_STR = 
					"author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\"";
	public static final String ANNO_RESULT = 
					"@SiteMap(" + ANNO_STR + ")";	
	public static final SiteMapAnnotation ANNOTATION = 
					new ExistingAnnotation(ANNO_RESULT, 1);
		
	public static final List<Import> IMPORT_LIST = 
			new ArrayList<>(Arrays.asList(
					new ExistingImport("import java.util.List;"),
					new ExistingImport("import org.openqa.selenium.By;"),
					new ExistingImport("import control_builder.*;"),
					new ExistingImport("import site_mapper.annotations.SiteMap;"),
					new ExistingImport("import object_models.panels.JsPanelWithIFrame;"),
					new ExistingImport("import object_models.pages.homepage.CoreData;")));
	
	public static final String IMPORT_RESULT =
			"import java.util.List;\n" +
			"import org.openqa.selenium.By;\n" +
			"import control_builder.*;\n" +
			"import site_mapper.annotations.SiteMap;\n" +
			"import object_models.panels.JsPanelWithIFrame;\n" +
			"import object_models.pages.homepage.CoreData;\n";
	
	public static final ExistingComment EXISTING_COMMENT = 
			new ExistingComment();
	public static final String COMMENT_RESULT = 
			"/**\n" +
			"* Generated Class.\n" +
			"* ----------------\n" +
			"* Source:  C:/site_map.xml\n" +
			"* Author:  SteveBrown\n" +
			"* Version: 1.0.0\n" +
			"* Created: 07/01/2022 08:53:56\n" +
			"*/\n";
	
	public static final String DECLARATION = 
			"public class EmployeeDetails extends JsPanelWithIFrame {";
	
	static {
		EXISTING_COMMENT
		.addLine("/**")
		.addLine("* Generated Class.")
		.addLine("* ----------------")
		.addLine("* Source:  C:/site_map.xml")
		.addLine("* Author:  SteveBrown")
		.addLine("* Version: 1.0.0")
		.addLine("* Created: 07/01/2022 08:53:56")
		.addLine("*/");
	}
	
	@Override
	public void setInPackage() {
		super.inPackage = new ExistingClassPackage(EXISTING_PACKAGE);
		
	}
	@Override
	public void setImports() {
		ImportList imports = new ImportList(IMPORT_LIST);
		super.imports = imports;		
	}
	@Override
	public void setComment() {
		super.comment = EXISTING_COMMENT;
	}
	@Override
	public void setDeclaration() {
		ClassDeclaration declaration = 
				new ClassDeclaration
					.ExistingDeclaration()
					.setDeclarationString(DECLARATION)
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
