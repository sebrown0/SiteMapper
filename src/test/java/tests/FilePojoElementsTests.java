/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import file.annotation.ExistingAnnotation;
import file.annotation.NewAnnotation;
import file.annotation.SiteMapAnnotation;
import file.class_file.ClassBody;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_package.ExistingClassPackage;
import file.class_package.NewClassPackage;
import file.comment.ExistingComment;
import file.comment.NewComment;
import file.imports.ExistingImport;
import file.imports.Import;
import file.imports.ImportList;
import file.method.Method;
import file.method.MethodList;
import file.variable.Argument;
import file.variable.ArgumentList;
import file.variable.ClassVariable;
import file.variable.Variable;
import helpers.TestClassBodyBuilder;
import helpers.TestClassFileBuilder;
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
	
//	private static final Element e1 = 
//			new Element()
//				.setName("save")
//				.setType("button")
//				.setText("Save")
//				.setFafa("fa fa-save");
//	
//	private static final Element e2 = 
//			new Element()
//				.setName("search")
//				.setType("button")
//				.setText("Search")
//				.setFafa("fa fa-search");
	
//	private static final SiteMapInfo info = 
//			new SiteMapInfo().setAuthor("SteveBrown").setVersion("1.0.0").setXmlSource("c:\\src");
	
//	private static final ElementClass menuItem = 
//			new MenuItem()
//				.setSiteMapInfo(info)
//				.setName("EmployeeDetails")
//				.setClassName("EmployeeDetails")
//				.setPackageName("a.payroll.Left.employees;")
//				.setMenuItemType(new MenuItemType().setAttributes(new JsPanelWithIFrame()))
//				.setElements(Arrays.asList(e1,e2));
		
	@Test
	void existing_package() {
		ExistingClassPackage cp = new ExistingClassPackage("package a.payroll.Left.employees;");
		
		assertEquals("package a.payroll.Left.employees;", cp.toString());
	}
	@Test
	void new_package() {
		NewClassPackage cp = new NewClassPackage("a.payroll.Left.employees");
		
		assertEquals("package a.payroll.Left.employees", cp.toString());
	}

	@Test
	void newImports() {
		ComponentWriter componentWriter = new ComponentWriterJsPanelWithIFrame();
		List<Import> imprtList = new ArrayList<>();
		imprtList.add(new ExistingImport("import java.util.List;"));
		imprtList.add(new ExistingImport("import control_builder.*;"));		
		ImportList imports = new ImportList(componentWriter.getImportNames());
		
		//DEPENDS ON THE IMPORTS IN ComponentWriterJsPanelWithIFrame
		assertEquals(
				"import java.util.List;\n" +
				"import org.openqa.selenium.By;\n" +
				"import control_builder.*;\n" +
				"import site_mapper.annotations.SiteMap;\n" +
				"/* Placeholder for missing import [JsPanelWithIFrame] */\n" +
				"/* Placeholder for missing import [CoreData] */\n" , 
				imports.toString());
	}	
	@Test
	void existingImports() {
		List<Import> imprtList = new ArrayList<>();
		imprtList.add(new ExistingImport("import java.util.List;"));
		imprtList.add(new ExistingImport("import control_builder.*;"));		
		ImportList imports = new ImportList(imprtList);		
		
		assertEquals("import java.util.List;\nimport control_builder.*;\n", imports.toString());
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
		ExistingAnnotation annotation = new ExistingAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")", 1);
		
		assertEquals("\t" + ANNOTATION_RESULT, annotation.toString());
	}
	@Test
	void testNewAnnotation() {
		SiteMapInfo info = new SiteMapInfo().setAuthor("SteveBrown").setVersion("1.0.0");
		NewAnnotation annotation = new NewAnnotation(info, 1);
		
		assertEquals("\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"" + info.getDate() + "\")", annotation.toString());
	}
	
	@Test
	void testVariable_withString() {
		ExistingAnnotation annotation = new ExistingAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")", 1);
		Variable v = 
				new ClassVariable
					.ClassVarFromString("public static final String PANEL_TITLE = Employee Details")
					.withAnnotation(annotation).build();
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tpublic static final String PANEL_TITLE = \"Employee Details\";", 
				v.toString());				 	
	}
	@Test
	void testVariable_withInt() {
		SiteMapAnnotation annotation = new ExistingAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")", 1);
		Variable v = 
				new Variable
					.ClassVarFromString("public static final int PANEL_TITLE = 1")
					.withAnnotation(annotation).build();
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
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
				.withAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")")
				.withDeclarationStr("private String aMethod(String str, Integer idx)")
				.addLine("Line1")
				.addLine("Line2")
				.build();		
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tprivate String aMethod(String str, Integer idx){\n" +
				"\t\tLine1\n\t\tLine2" +
				"\n\t}", 
				m.toString());				 	
	}	
	@Test
	void testMethodList() {
		Method m1 = new Method.ExistingMethodBuilder(1)
				.withAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")")
				.withDeclarationStr("private String aMethodOne(String str, Integer idx)")
				.addLine("Line1")
				.addLine("Line2")
				.build();
		Method m2 = new Method.ExistingMethodBuilder(1)
				.withAnnotation("@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")")
				.withDeclarationStr("private String aMethodTwo(boolean b, Integer idx)")
				.addLine("Line1")
				.addLine("Line2")
				.build();		
		
		MethodList ml = new MethodList();
		ml.addMethod(m1).addMethod(m2);
		
		assertEquals(
				"\t@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")\n" +
				"\tprivate String aMethodOne(String str, Integer idx){\n" +
				"\t\tLine1\n" +
				"\t\tLine2\n" +
				"\t}\n" +
				"\t@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")\n" +
				"\tprivate String aMethodTwo(boolean b, Integer idx){\n" +
				"\t\tLine1\n" +
				"\t\tLine2\n" +
				"\t}", 
				String.valueOf(ml.toString()));
	}
	
	@Test
	void classBody_fromTestBodyBuilder() {
		ClassBody body = new TestClassBodyBuilder().build();
		assertEquals(
				"\t@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")\n"
				+ "\tpublic static final int PANEL_TITLE = 1;\n"
				+ "\tpublic static final String MENU_TITLE = \"Employee Details\";\n"
				+ "\n"
				+ "\t@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")\n"
				+ "\tpublic EmployeeDetails(CoreData coreData){\n"
				+ "\t}\n"
				+ "\n"
				+ "\t@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")\n"
				+ "\tprivate String aMethod(String str, Integer idx){\n"
				+ "\t\tLine1\n"
				+ "\t\tLine2\n"
				+ "\t}", body.toString());
	}
	
	@Test
	void classFile_fromTestClassFileBuilder() {
		ClassFile clazzFile = new TestClassFileBuilder().build();

		assertEquals(
				"package a.payroll.Left.employees;\n"
				+ "\n"
				+ "import java.util.List;\n"
				+ "import control_builder.*;\n"
				+ "\n"
				+ "/**\n"
				+ "* Generated Class.\n"
				+ "* ----------------\n"
				+ "* Source:  C:/site_map.xml\n"
				+ "* Author:  SteveBrown\n"
				+ "* Version: 1.0.0\n"
				+ "* Created: 07/01/2022 08:53:56\n"
				+ "*/\n"
				+ "public class EmployeeDetails extends JsPanelWithIFrame {\n"
				+ "\t@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")\n"
				+ "\tpublic static final int PANEL_TITLE = 1;\n"
				+ "\tpublic static final String MENU_TITLE = \"Employee Details\";\n"
				+ "\n"
				+ "\t@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")\n"
				+ "\tpublic EmployeeDetails(CoreData coreData){\n"
				+ "\t}\n"
				+ "\n"
				+ "\t@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")\n"
				+ "\tprivate String aMethod(String str, Integer idx){\n"
				+ "\t\tLine1\n"
				+ "\t\tLine2\n"
				+ "\t}\n"
				+ "}", 
				clazzFile.toString());
	}
		
	@Test
	void newClassFileBuilder() {
		
//		NewClassFileBuilder builder = 
//				new ClassFile.NewClassFileBuilder(menuItem);
//		
//		ClassFile classFile = builder.build();
		
//		System.out.println(classFile.toString()); // TODO - remove or log 	
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
