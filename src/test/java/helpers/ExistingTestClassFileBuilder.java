/**
 * 
 */
package helpers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_file.ClassFile.ClassBuilder;
import file.class_file.body.ClassBody;
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
public class ExistingTestClassFileBuilder extends ClassBuilder {
	public final String AUTHOR;
	public final String VERSION;
	public final String XML_SOURCE;
	public final String DATE;
	public final String TIME;
	
	private ExistingTestClassBodyBuilder bodyBuilder;
	
	public ExistingTestClassFileBuilder() {
		AUTHOR = "SteveBrown";
		VERSION = "1.0.0";
		XML_SOURCE = "C:/site_map.xml";
		DATE = "07/01/2022";
		TIME = "08:53:56";
		setExistingComment();
		this.bodyBuilder = new ExistingTestClassBodyBuilder(this);		 	
	}
	public ExistingTestClassFileBuilder(
			String aUTHOR, String vERSION, String xML_SOURCE, String dATE, String tIME) {
		
		AUTHOR = aUTHOR;
		VERSION = vERSION;
		XML_SOURCE = xML_SOURCE;
		DATE = dATE;
		TIME = tIME;
		setExistingComment();
		this.bodyBuilder = new ExistingTestClassBodyBuilder(this);
	}

	public static final String EXISTING_PACKAGE = 
				"package employees;";
	public static final String NEW_PACKAGE = 
				"employees";
	public static final String PACKAGE_RESULT = 
				"package employees;";
	
	public String ANNO_STR() { 
		return 
				"author=\"" + AUTHOR + "\", " + 
				"version=\"" + VERSION + "\", " + 
				"date=\"" + DATE + "\"";
	}
	public String ANNO_RESULT() { 
		return "\t@SiteMap(" + ANNO_STR() + ")";
	}
	public SiteMapAnnotation ANNOTATION() { 
		return new ExistingAnnotation(ANNO_RESULT(), 1);
	}
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
	
	public String COMMENT_RESULT() { 
		return	"/**\n" +
			"* Generated Class.\n" +
			"* ----------------\n" +
			"* Source:  " + XML_SOURCE + "\n" +
			"* Author:  " + AUTHOR + "\n" +
			"* Version: " + VERSION + "\n" +
			"* Created: " + DATE + " " + TIME + "\n" +
			"*/\n\n"; //Extra line feed for space between comment and class declaration.
	}
	public static final String DECLARATION = 
			"public class EmployeeDetails extends JsPanelWithIFrame {";
	
	public String CLASS_RESULT_FULL() { 
		return
			PACKAGE_RESULT +
			"\n\n" +
			IMPORT_RESULT +
			"\n" +				
			COMMENT_RESULT() +
			DECLARATION +
			"\n" +
			bodyBuilder.BODY_RESULT_WITH_EXTRA_METHOD() +
			"\n\n" +			
			"}";
	}
	public String CLASS_RESULT_WITHOUT_EXTRA_METHOD() {
		return
			PACKAGE_RESULT +
			"\n\n" +
			IMPORT_RESULT +
			"\n" +				
			COMMENT_RESULT() +
			DECLARATION +
			"\n" +
			bodyBuilder.BODY_RESULT() +
			"\n" +			
			"}";
	}
	
	private void setExistingComment() {
		EXISTING_COMMENT
		.addLine("/**")
		.addLine("* Generated Class.")
		.addLine("* ----------------")
		.addLine("* Source:  " + XML_SOURCE)
		.addLine("* Author:  " + AUTHOR)
		.addLine("* Version: " + VERSION)
		.addLine("* Created: " + DATE + " " + TIME)
		.addLine("*/");
//		.addLine("*\n");
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
		System.out.println(EXISTING_COMMENT); // TODO - remove or log 	
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
		this.setClassBody(bodyBuilder.build());
		return new ClassFile(this);
	}

}
