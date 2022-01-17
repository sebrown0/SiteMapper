/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import file.annotation.ExistingAnnotation;
import file.annotation.NewAnnotation;
import file.annotation.SiteMapAnnotation;
import file.class_file.ClassBody;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_file.ClassFile.NewClassFileBuilder;
import file.class_file.ExistingConstructor;
import file.class_package.ExistingClassPackage;
import file.class_package.NewClassPackage;
import file.comment.ExistingComment;
import file.comment.NewComment;
import file.imports.ExistingImport;
import file.imports.NewImport;
import file.method.ExistingMethodBody;
import file.method.Method;
import file.variable.Argument;
import file.variable.ClassVariable;
import file.variable.MethodVariable;
import site_mapper.creators.ComponentWriter;
import site_mapper.creators.ComponentWriterJsPanelWithIFrame;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class FilePojoElementsTests {
	private static final String ANNOTATION_RESULT = 
			"@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")";
	
	private static final String COMMENT_RESULT = 
			"/**\n" +
			"* Generated Class.\n" +
			"* ----------------\n" +
			"* Source:  C:/site_map.xml\n" +
			"* Author:  SteveBrown\n" +
			"* Version: 1.0.0\n" +
			"* Created: 07/01/2022 08:53:56\n" +
			"*/\n";
	
	@Test
	void existing_package() {
		ExistingClassPackage cp = new ExistingClassPackage("package a.payroll.Left.employees;");
		
		assertEquals("package a.payroll.Left.employees;\n\n", cp.toString());
	}
	@Test
	void new_package() {
		NewClassPackage cp = new NewClassPackage("a.payroll.Left.employees");
		
		assertEquals("package a.payroll.Left.employees;\n\n", cp.toString());
	}
	
	@Test
	void testExistingAnnotation() {		
		ExistingAnnotation annotation = new ExistingAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")");
		
		assertEquals(ANNOTATION_RESULT, annotation.toString());
	}
	@Test
	void testNewAnnotation() {
		SiteMapInfo info = new SiteMapInfo().setAuthor("SteveBrown").setVersion("1.0.0");
		NewAnnotation annotation = new NewAnnotation(info);
		
		assertEquals("@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"" + info.getDate() + "\")", annotation.toString());
	}
	
	@Test
	void testVariable_withString() {
		ExistingAnnotation annotation = new ExistingAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")");
		ClassVariable v = new ClassVariable();
		v
			.setAnnotation(annotation)
			.setType("public static final String")
			.setName("PANEL_TITLE")
			.setValue("Employee Details");
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tpublic static final String PANEL_TITLE = \"Employee Details\";", 
				v.toString());				 	
	}
	@Test
	void testVariable_withInt() {
		SiteMapAnnotation annotation = new ExistingAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")");
		MethodVariable v = new MethodVariable();
		v
			.setAnnotation(annotation)
			.setType("public static final int")
			.setName("PANEL_TITLE")
			.setValue("1");
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tpublic static final int PANEL_TITLE = 1", 
				v.toString());				 	
	}	
	@Test
	void testClassVariable_fromStr_with_final() {
		ClassVariable v = new ClassVariable();
		v.setFromString("public final String MENU_TITLE = \"Employee Details\";");
		
		assertEquals("public final String MENU_TITLE = \"Employee Details\";", v.toString());
	}
	@Test
	void testClassVariable_fromStr_with_staticFinal() {
		ClassVariable v = new ClassVariable();
		v.setFromString("public static final String MENU_TITLE = \"Employee Details\";");
		 	
		assertEquals("public static final String MENU_TITLE = \"Employee Details\";", v.toString());
	}
	@Test
	void testClassVariable_fromStr_with_value() {
		ClassVariable v = new ClassVariable();
		v.setFromString("public String MENU_TITLE = \"Employee Details\";");
		 	
		assertEquals("public String MENU_TITLE = \"Employee Details\";", v.toString());
	}
	@Test
	void testClassVariable_fromStr() {
		ClassVariable v = new ClassVariable();
		v.setFromString("public String MENU_TITLE;");
		 	
		assertEquals("public String MENU_TITLE;", v.toString());
	}
	@Test
	void testArgument() {
		Argument v = new Argument("String", "str");		
		
		assertEquals("String str", v.toString());				 	
	}
	
	@Test
	void existingComment() {
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
		
		assertEquals(COMMENT_RESULT, comment.toString());		
	}
	@Test
	void newComment() {
		SiteMapInfo info = new SiteMapInfo();
		NewComment comment = new NewComment(
				info
					.setXmlSource("C:/site_map.xml")
					.setAuthor("SteveBrown")
					.setVersion("1.0.0"));
				
		assertEquals(
				"/**\n" +
				"* Generated Class.\n" +
				"* ----------------\n" +
				"* Source:  C:/site_map.xml\n" +
				"* Author:  SteveBrown\n" +
				"* Version: 1.0.0\n" +
				"* Created: " + info.getTimeStamp() +
				"\n*/\n", 
				comment.toString());		
	}
	
	@Test
	void methodBody() {
		ExistingMethodBody body = new ExistingMethodBody();
		body.addLine("Line1").addLine("Line2");
		
		assertEquals("\t\tLine1\n\t\tLine2\n", body.toString());
	}

	@Test
	void newImports() {
		ComponentWriter componentWriter = new ComponentWriterJsPanelWithIFrame();
		NewImport imps = new NewImport();
		imps.setLines(componentWriter.getImportNames());

		//DEPENDS ON THE IMPORTS IN ComponentWriterJsPanelWithIFrame
		assertEquals(
				"import java.util.List;\n" +
				"import org.openqa.selenium.By;\n" +
				"import control_builder.*;\n" +
				"import site_mapper.annotations.SiteMap;\n" +
				"/* Placeholder for missing import [JsPanelWithIFrame] */\n" +
				"/* Placeholder for missing import [CoreData] */\n" , 
				imps.toString());
	}	
	@Test
	void existingImports() {
		ExistingImport imps = new ExistingImport();
		imps.addLine("import java.util.List;").addLine("import control_builder.*;");
		
		assertEquals("import java.util.List;\nimport control_builder.*;\n\n", imps.toString());
	}
	
	@Test 
	void classDeclaration_without_extends_or_implements(){
		ClassDeclaration declaration = new ClassDeclaration("public", "EmployeeDetails");
		assertEquals("public class EmployeeDetails {\n", declaration.toString());
	}
	@Test 
	void classDeclaration_with_extends(){
		ClassDeclaration declaration = new ClassDeclaration("public", "EmployeeDetails");
		declaration.addExtended("Ext1").addExtended("Ext2");
		
		assertEquals("public class EmployeeDetails extends Ext1, Ext2 {\n", declaration.toString());
	}
	@Test 
	void classDeclaration_with_implements(){
		ClassDeclaration declaration = new ClassDeclaration("public", "EmployeeDetails");
		declaration.addImplemented("Imp1").addImplemented("Imp2");
		
		assertEquals("public class EmployeeDetails implements Imp1, Imp2 {\n", declaration.toString());
	}
	@Test 
	void classDeclaration_with_extends_implements(){
		ClassDeclaration declaration = new ClassDeclaration("public", "EmployeeDetails");
		declaration
			.addExtended("Ext1").addExtended("Ext2")
			.addImplemented("Imp1").addImplemented("Imp2");
		
		assertEquals("public class EmployeeDetails extends Ext1, Ext2 implements Imp1, Imp2 {\n", declaration.toString());
	}
	
	@Test
	void constructor() {
		List<String> lines = new ArrayList<>();
		lines.add("\tpublic EmployeeDetails(CoreData coreData) {");
		lines.add("\t\tsuper(coreData,PANEL_TITLE);");
		lines.add("\t\tbuildMyControls();");
		lines.add("\t}");
		
		ExistingAnnotation annotation = new ExistingAnnotation("author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\"");
		ExistingConstructor cnstr = new ExistingConstructor(lines);
		cnstr.setAnnotation(annotation);
		
		System.out.println(cnstr.toString()); // TODO - remove or log 	
	}
	
	@Test
	void testMethod() {
		ExistingMethodBody body = new ExistingMethodBody();
		body.addLine("Line1").addLine("Line2");
		
		Method m = new Method();
		m
			.setAnnotation(new ExistingAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")"))
			.setModifier("private")
			.setReturnType("String")
			.setName("aMethod")
			.addVariables(new Argument("String", "str"))
			.addVariables(new Argument("Integer", "idx"))
			.setBody(body);
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tprivate String aMethod(String str, Integer idx){\n" +
				"\t\tLine1\n\t\tLine2" +
				"\n\t}", 
				m.toString());				 	
	}	
	
	@Test
	void testClassBody() {		 	
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tpublic static final String PANEL_NAME = \"Employee Details\";\n" +
				"\tprivate int idx;\n\n" +
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tprivate String aMethod(String str, Integer idx){\n" +
				"\t\tLine1\n\t\tLine2\n" +
				"\t}\n"
				, getTestClassBody().toString());
	}	
	
	@Test
	void existing_classFile() {
		ExistingComment comment = 
			(ExistingComment) new ExistingComment()
				.addLine("/**")
				.addLine("* Generated Class.")
				.addLine("* ----------------")
				.addLine("* Source:  C:/site_map.xml")
				.addLine("* Author:  SteveBrown")
				.addLine("* Version: 1.0.0")
				.addLine("* Created: 07/01/2022 08:53:56")
				.addLine("*/");
		
		ExistingImport imprt = 
				(ExistingImport) new ExistingImport().addLine("import java.util.List;").addLine("import control_builder.*;");
		
		ClassFile clazz = 
				new ClassFile
					.ExistingClassFileBuilder(
						new ExistingClassPackage("package a.payroll.Left.employees;"), 
						imprt, 
						comment, 
						new ClassDeclaration("public", "EmployeeDetails").addExtended("JsPanelWithIFrame"), 
						getTestClassBody())
					.build();
				
//		System.out.println(clazz.toString()); // TODO - remove or log 	
		assertEquals(
			"package a.payroll.Left.employees;\n\n" +
			"import java.util.List;\nimport control_builder.*;\n\n" +
			COMMENT_RESULT +
			"public class EmployeeDetails extends JsPanelWithIFrame {\n" +
			"\t" + ANNOTATION_RESULT + "\n" +
			"\tpublic static final String PANEL_NAME = \"Employee Details\";\n" +
			"\tprivate int idx;\n\n" +
			"\t" + ANNOTATION_RESULT + "\n" +
			"\tprivate String aMethod(String str, Integer idx){\n" +
			"\t\tLine1\n\t\tLine2\n\t}\n" +
			"\n}"
			,
			clazz.toString());
		
	}
	
	private ClassBody getTestClassBody() {
		SiteMapAnnotation annotation = new ExistingAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")");
		
		ClassVariable v1 = new ClassVariable();
		v1
			.setAnnotation(annotation)
			.setType("public static final String")
			.setName("PANEL_NAME")
			.setValue("Employee Details");
		
		ClassVariable v2 = new ClassVariable();
		v2		
			.setType("private int")
			.setName("idx");
		
		ExistingMethodBody methodBody = new ExistingMethodBody();
		methodBody.addLine("Line1").addLine("Line2");
		
		Method m = new Method();
		m
			.setAnnotation(annotation)
			.setModifier("private")
			.setReturnType("String")
			.setName("aMethod")
			.addVariables(new Argument("String", "str"))
			.addVariables(new Argument("Integer", "idx"))
			.setBody(methodBody);
						 	
		ClassBody classBody = new ClassBody();
		classBody
			.addVariable(v1)
			.addVariable(v2)
			.addMethod(m);
		
		return classBody;
	}
	
	@Test
	void newClassFileBuilder() {
		ComponentWriterJsPanelWithIFrame componentWriter = new ComponentWriterJsPanelWithIFrame();
		SiteMapInfo info = new SiteMapInfo().setAuthor("SteveBrown").setVersion("1.0.0");
		
		NewClassFileBuilder builder = 
				new ClassFile.NewClassFileBuilder(componentWriter, info);
		
		ClassFile classFile = builder.build();
		
		System.out.println(classFile.toString()); // TODO - remove or log 	
	}
	
//	@Test
//	void newClassFileBuilder() {
//		NewImport imprt = 
//				(NewImport) new NewImport().addLine("import java.util.List;").addLine("import control_builder.*;");
//		
//		NewComment comment = 
//			new NewComment(
//				new SiteMapInfo()
//					.setXmlSource("C:/site_map.xml")
//					.setAuthor("SteveBrown")
//					.setVersion("1.0.0"));
//		
//		
//		NewClassFileBuilder builder = new ClassFile.NewClassFileBuilder();
//		builder
//			.setInPackage(new NewClassPackage("package a.payroll.Left.employees"))
//			.setImports(imprt)
//			.setComment(comment)
//			.setDeclaration(null)
//			.setClassBody(getTestClassBody());
//		
//		ClassFile classFile = builder.build();
//		
//		System.out.println(classFile.toString()); // TODO - remove or log 	
//	}
}
