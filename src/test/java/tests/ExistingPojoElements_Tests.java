/**
 * 
 */
package tests;

import static helpers.ExistingTestClassBodyBuilder.BODY_RESULT;
import static helpers.ExistingTestClassFileBuilder.ANNOTATION;
import static helpers.ExistingTestClassFileBuilder.ANNO_RESULT;
import static helpers.ExistingTestClassFileBuilder.CLASS_RESULT;
import static helpers.ExistingTestClassFileBuilder.COMMENT_RESULT;
import static helpers.ExistingTestClassFileBuilder.EXISTING_COMMENT;
import static helpers.ExistingTestClassFileBuilder.EXISTING_PACKAGE;
import static helpers.ExistingTestClassFileBuilder.PACKAGE_RESULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import file.class_file.ClassBody;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_package.ExistingClassPackage;
import file.imports.ImportList;
import file.method.Method;
import file.method.MethodList;
import file.variable.Argument;
import file.variable.ArgumentList;
import file.variable.ClassVariable;
import file.variable.Variable;
import helpers.ExistingTestClassBodyBuilder;
import helpers.ExistingTestClassFileBuilder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Tests for all elements that make up an 
 * Existing Class File.
 * 
 * The overall object is built from ExistingClassFileBuilder.
 * Objects that make up the ClassFile are built similarly.
 * 
 * There are two helper classes: 
 * 	ExistingTestClassFileBuilder/ExistingTestClassBodyBuilder.
 * 
 * Data is either taken from the them or the test method.
 * ********************************************************
 * If the ClassFile is created with the helpers then this *
 * should be an exact copy of TestClass.									*
 * 																												*
 * And the 'build method' mirrors that of building the		*
 * ClassFile from an existing class, i.e. TestClass				*
 * ********************************************************
 *  
 * ScannerTests does the same thing but takes the data from 
 * an existing class: src/test/resources/test_data/TestClass.java
 */
class ExistingPojoElements_Tests {	
	
	@Test
	void existing_package() {
		ExistingClassPackage cp = new ExistingClassPackage(EXISTING_PACKAGE);		
		assertEquals(PACKAGE_RESULT, cp.toString());
	}
	
	@Test
	void existingImports() {
		ImportList imports = new ImportList(ExistingTestClassFileBuilder.IMPORT_LIST);		
		
		assertEquals(
				"import java.util.List;\n" + 
				"import org.openqa.selenium.By;\n" + 
				"import control_builder.*;\n" +
				"import site_mapper.annotations.SiteMap;\n" +
				"import object_models.panels.JsPanelWithIFrame;\n" +
				"import object_models.pages.homepage.CoreData;\n",
				imports.toString());
	}

	@Test
	void existingComment() {		
		assertEquals(COMMENT_RESULT, EXISTING_COMMENT.toString());		
	}
	
	@Test 
	void existing_classDeclaration_without_extends_or_implements(){
		ClassDeclaration declaration = 
				new ClassDeclaration
					.ExistingDeclaration()
					.setDeclarationString("public class EmployeeDetails {")
					.build();
		
		assertEquals("public class EmployeeDetails {", declaration.toString());
	}
	@Test 
	void existing_classDeclaration_with_extends(){
		ClassDeclaration declaration = 
				new ClassDeclaration
					.ExistingDeclaration()
					.setDeclarationString("public class EmployeeDetails extends JsPanelWithIFrame {")
					.build();
		
		assertEquals("public class EmployeeDetails extends JsPanelWithIFrame {", declaration.toString());
	}
	@Test 
	void existing_classDeclaration_with_implements(){
		ClassDeclaration declaration = 
				new ClassDeclaration
					.ExistingDeclaration()
					.setDeclarationString("public class EmployeeDetails implements Imp1, Imp2 {")
					.build();
		
		assertEquals("public class EmployeeDetails implements Imp1, Imp2 {", declaration.toString());
	}
	@Test 
	void existing_classDeclaration_with_extends_implements(){
		ClassDeclaration declaration = 
				new ClassDeclaration
					.ExistingDeclaration()
					.setDeclarationString("public class EmployeeDetails extends JsPanelWithIFrame implements Imp1, Imp2 {")
					.build();
		
		assertEquals("public class EmployeeDetails extends JsPanelWithIFrame implements Imp1, Imp2 {", declaration.toString());
	}
	
	@Test
	void testExistingAnnotation() {		
		assertEquals("\t" + ANNO_RESULT, ANNOTATION.toString());
	}
	
	@Test
	void testVariable_withString() {
		Variable v = 
				new ClassVariable
					.ClassVarFromString("public static final String PANEL_TITLE = Employee Details")
					.withAnnotation(ANNOTATION).build();
		
		assertEquals(
				"\t" + ANNO_RESULT + "\n" +
				"\tpublic static final String PANEL_TITLE = \"Employee Details\";", 
				v.toString());				 	
	}
	@Test
	void testVariable_withInt() {		
		Variable v = 
				new Variable
					.ClassVarFromString("public static final int PANEL_TITLE = 1")
					.withAnnotation(ANNOTATION).build();
		
		assertEquals(
				"\t" + ANNO_RESULT + "\n" +
				"\tpublic static final int PANEL_TITLE = 1;", 
				v.toString());				 	
	}	
	@Test
	void testClassVariable_fromStr_with_final() {
		Variable v = 
				new ClassVariable
					.ClassVarFromString("public final String MENU_TITLE = \"Employee Details\";")
					.build();
				
		assertEquals("\tpublic final String MENU_TITLE = \"Employee Details\";", v.toString());
	}
	@Test
	void testClassVariable_fromStr_with_staticFinal() {
		Variable v = 
				new ClassVariable
					.ClassVarFromString("public static final String MENU_TITLE = \"Employee Details\";")
					.build();
				 	
		assertEquals("\tpublic static final String MENU_TITLE = \"Employee Details\";", v.toString());
	}
	@Test
	void testClassVariable_fromStr_with_value() {
		Variable v = 
				new ClassVariable
					.ClassVarFromString("public String MENU_TITLE = \"Employee Details\";")
					.build();
				 	
		assertEquals("\tpublic String MENU_TITLE = \"Employee Details\";", v.toString());
	}
	@Test
	void testClassVariable_fromStr() {
		Variable v = 
				new ClassVariable
					.ClassVarFromString("public String MENU_TITLE;")
					.build();
				 	
		assertEquals("\tpublic String MENU_TITLE;", v.toString());
	}
	@Test
	void testArgument() {
		Argument v = new Argument("String", "str");		
		
		assertEquals("String str", v.toString());				 	
	}
	@Test
	void listOfArguments_good() {
		ArgumentList args = new ArgumentList();
		args.createArgList("String str, int idx, CoreData cd");
		
		assertEquals("String str, int idx, CoreData cd", args.toString());
	}
	@Test
	void listOfArguments_withBadArg() {
		ArgumentList args = new ArgumentList();
		args.createArgList("String str, idx, CoreData cd");
		
		assertEquals("String str, CoreData cd", args.toString());
	}
	@Test
	void listOfArguments_nullArgs() {
		ArgumentList args = new ArgumentList();
		args.createArgList(null);
		
		assertEquals("", args.toString());
	}
	@Test
	void listOfArguments_noArgs() {
		ArgumentList args = new ArgumentList();
		args.createArgList("");
		
		assertEquals("", args.toString());
	}
			
	@Test
	void testMethod() {
		Method m = new Method.ExistingMethodBuilder(1)
				.withAnnotation(ANNOTATION.toString())
				.withDeclarationStr("private String aMethod(String str, Integer idx)")
				.addLine("Line1")
				.addLine("Line2")
				.build();		
		
		assertEquals(
				"\t" + ANNO_RESULT + "\n" +
				"\tprivate String aMethod(String str, Integer idx){\n" +
				"\t\tLine1\n\t\tLine2" +
				"\n\t}", 
				m.toString());				 	
	}	
	@Test
	void testMethodList() {
		Method m1 = new Method.ExistingMethodBuilder(1)
				.withAnnotation(ANNOTATION.toString())
				.withDeclarationStr("private String aMethodOne(String str, Integer idx)")
				.addLine("Line1")
				.addLine("Line2")
				.build();
		Method m2 = new Method.ExistingMethodBuilder(1)
				.withAnnotation(ANNOTATION.toString())
				.withDeclarationStr("private String aMethodTwo(boolean b, Integer idx)")
				.addLine("Line1")
				.addLine("Line2")
				.build();		
		
		MethodList ml = new MethodList();
		ml.addMethod(m1).addMethod(m2);
		
		assertEquals(
				"\t" + ANNO_RESULT + "\n" +
				"\tprivate String aMethodOne(String str, Integer idx){\n" +
				"\t\tLine1\n" +
				"\t\tLine2\n" +
				"\t}\n" +
				"\t" + ANNO_RESULT + "\n" +
				"\tprivate String aMethodTwo(boolean b, Integer idx){\n" +
				"\t\tLine1\n" +
				"\t\tLine2\n" +
				"\t}", 
				ml.toString());
	}
	
	@Test
	void classBody_fromTestBodyBuilder() {
		ClassBody body = new ExistingTestClassBodyBuilder().build();
		assertEquals(BODY_RESULT, body.toString());		
	}
	
	@Test
	void classFile_fromTestClassFileBuilder() {
		ClassFile clazzFile = new ExistingTestClassFileBuilder().build();		
		assertEquals(CLASS_RESULT, clazzFile.toString());
	}		
}
