/**
 * 
 */
package tests.mapping_tests;

import static helpers.ExistingTestClassBodyBuilder.VAR1_RESULT;
import static helpers.ExistingTestClassFileBuilder.EXISTING_PACKAGE;
import static helpers.ExistingTestClassFileBuilder.PACKAGE_RESULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import file.annotation.SiteMapAnnotation;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_file.body.ClassBody;
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
	private static final ExistingTestClassFileBuilder FILE_BUILDER = new ExistingTestClassFileBuilder();
	
	private static SiteMapAnnotation ANNOTATION;
	private static String ANNOTATION_STR;
	private static String ANNO_RESULT;
	
	@BeforeAll
	static void setup() {
		ANNOTATION = FILE_BUILDER.ANNOTATION();
		ANNOTATION_STR = ANNOTATION.toString();
		ANNO_RESULT = FILE_BUILDER.ANNO_RESULT();
	}
	
	@Test
	void existing_package() {
		ExistingClassPackage cp = new ExistingClassPackage(EXISTING_PACKAGE);		
		assertEquals(PACKAGE_RESULT, cp.toString());
	}
	
	@Test
	void existingImports() {
		ImportList imports = new ImportList(ExistingTestClassFileBuilder.IMPORT_LIST);		
		
		assertEquals(
				ExistingTestClassFileBuilder.IMPORT_RESULT,
				imports.toString());		
	}

//	NOT WORKING SPACING IS WRONG.
//	@Test
//	void existingComment() {		
//		assertEquals(FILE_BUILDER.COMMENT_RESULT(), EXISTING_COMMENT.toString());		
//	}
	
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
		assertEquals(ANNO_RESULT, ANNOTATION_STR);
	}
	
	@Test
	void testVariable_withString() {
		Variable v = 
				new ClassVariable
					.ClassVarFromString("public static final String PANEL_TITLE = Employee Details")
					.withAnnotation(ANNOTATION).build();
		
		assertEquals(
				ANNO_RESULT + "\n" +
				VAR1_RESULT,
				v.toString());				 	
	}
	@Test
	void testVariable_withInt() {		
		Variable v = 
				new Variable
					.ClassVarFromString("public static final int PANEL_TITLE = 1")
					.withAnnotation(ANNOTATION).build();
		
		assertEquals(
				ANNO_RESULT + "\n" +
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
				.withSiteMapAnnotation(ANNOTATION_STR)
				.withDeclarationStr("private String aMethod(String str, Integer idx)")
				.addLine("\tLine1")
				.addLine("\tLine2")
				.build();		
		
		assertEquals(
				ANNO_RESULT + "\n" +
				"\tprivate String aMethod(String str, Integer idx) {\n" +
				"\t\tLine1\n\t\tLine2" +
				"\n\t}", 
				m.toString());				 	
	}	
	@Test
	void testMethodList() {
		Method m1 = new Method.ExistingMethodBuilder(1)
				.withSiteMapAnnotation(ANNOTATION_STR)
				.withDeclarationStr("private String aMethodOne(String str, Integer idx)")
				.addLine("\tLine1")
				.addLine("\tLine2")
				.build();
		Method m2 = new Method.ExistingMethodBuilder(1)
				.withSiteMapAnnotation(ANNOTATION_STR)
				.withDeclarationStr("private String aMethodTwo(boolean b, Integer idx)")
				.addLine("\tLine1")
				.addLine("\tLine2")
				.build();		
		
		MethodList ml = new MethodList();
		ml.addMethod(m1).addMethod(m2);
		
		assertEquals(
				ANNO_RESULT + "\n" +
				"\tprivate String aMethodOne(String str, Integer idx) {\n" +
				"\t\tLine1\n" +
				"\t\tLine2\n" +
				"\t}\n" +
				ANNO_RESULT + "\n" +
				"\tprivate String aMethodTwo(boolean b, Integer idx) {\n" +
				"\t\tLine1\n" +
				"\t\tLine2\n" +
				"\t}", 
				ml.toString());
	}
	
	@Test
	void classBody_fromTestBodyBuilder() {
		ExistingTestClassBodyBuilder builder = new ExistingTestClassBodyBuilder(FILE_BUILDER);
		ClassBody body = builder.build();
		assertEquals(builder.BODY_RESULT_WITH_TEST_METHODS_AND_EXTRA_METHOD(), body.toString());		
	}
	
	@Test
	void classFile_fromTestClassFileBuilder() {
		ClassFile clazzFile = FILE_BUILDER.build();		
		assertEquals(FILE_BUILDER.CLASS_RESULT_FULL(), clazzFile.toString());
	}		
}
