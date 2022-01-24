/**
 * 
 */
package tests;

import static helpers.NewTestClassFileBuilder.*;
import static helpers.ExistingTestClassBodyBuilder.BUILD_MY_CONTROLS_DEC;
import static helpers.ExistingTestClassBodyBuilder.CNSTR_LINES;
import static helpers.ExistingTestClassBodyBuilder.CONSTRUCTOR_DEC;
import static helpers.ExistingTestClassBodyBuilder.CONTROLS_LINES;
import static helpers.ExistingTestClassBodyBuilder.NOT_FROM_SITEMAPPER_DEC;
import static helpers.ExistingTestClassBodyBuilder.NOT_FROM_SITEMAPPER_LINES;
import static helpers.ExistingTestClassBodyBuilder.VAR1_RESULT;
import static helpers.ExistingTestClassBodyBuilder.VAR2_RESULT;
import static helpers.ExistingTestClassBodyBuilder.VAR3_RESULT;
import static helpers.ExistingTestClassFileBuilder.ANNOTATION;
import static helpers.ExistingTestClassFileBuilder.ANNO_RESULT;
import static helpers.ExistingTestClassFileBuilder.COMMENT_RESULT;
import static helpers.ExistingTestClassFileBuilder.DECLARATION;
import static helpers.ExistingTestClassFileBuilder.IMPORT_RESULT;
import static helpers.ExistingTestClassFileBuilder.NEW_PACKAGE;
import static helpers.ExistingTestClassFileBuilder.PACKAGE_RESULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import file.annotation.NewAnnotation;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_file.body.ClassBody;
import file.class_file.body.ControlBuilder;
import file.class_package.NewClassPackage;
import file.comment.NewComment;
import file.imports.ImportList;
import file.method.Method;
import file.method.MethodList;
import file.variable.ClassVariable;
import file.variable.Variable;
import file.variable.Variables;
import helpers.ExistingTestClassBodyBuilder;
import helpers.ExistingTestClassFileBuilder;
import helpers.NewTestClassFileBuilder;
import site_mapper.creators.ComponentWriter;
import site_mapper.creators.ComponentWriterJsPanelWithIFrame;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Tests for all elements that make up a new Class File.
 * That is a ClassFile built with data from the XML file.
 * 
 * These tests are not about unmarshalling the XML but to
 * test objects that have been unmarshalled are mapped 
 * correctly.
 * 
 * The ClassFile should be tested against:
 *  	ExistingTestClassFileBuilder.CLASS_RESULT
 * 
 */
class NewPojoElements_Tests {	
	
	/*
	 * Elements of the class without using a builder(s).
	 */
	@Test
	void new_package() {
		NewClassPackage cp = new NewClassPackage(NEW_PACKAGE);		
		assertEquals(PACKAGE_RESULT, cp.toString());
	}

	@Test
	void newImports() {
		ComponentWriter componentWriter = new ComponentWriterJsPanelWithIFrame();
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
	
	/*
	 * DECLARATIONS CANNOT BE INSTANTIATED WITHOUT A BUILDER.
	 * SO THEY ARE NOT INCLUDED IN THE TESTS.
	 */
	
	@Test
	void testNewAnnotation() {
		SiteMapInfo info = new SiteMapInfo().setAuthor("SteveBrown").setVersion("1.0.0");
		NewAnnotation annotation = new NewAnnotation(info, 1);
		
		assertEquals("\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"" + info.getDate() + "\")", annotation.toString());
	}
	
	/*
	 * VARIABLES CANNOT BE INSTANTIATED WITHOUT A BUILDER.
	 * SO THEY ARE NOT INCLUDED IN THE TESTS.
	 */
	
	/*
	 * METHODS CANNOT BE INSTANTIATED WITHOUT A BUILDER.
	 * SO THEY ARE NOT INCLUDED IN THE TESTS.
	 */
		
	
	/*	 
	 * Elements of the class using a builder(s).
	 */
	@Test
	void testPackage() {
		ClassFile classFile = NewTestClassFileBuilder.getClassFile();
		assertEquals(PACKAGE_RESULT, classFile.getPackageStr());		
	}
	
	@Test
	void testImports() {
		ClassFile classFile = NewTestClassFileBuilder.getClassFile();
		assertEquals(IMPORT_RESULT, classFile.getImportStr());		
	}
	
	@Test
	void testComment() {
		ClassFile classFile = NewTestClassFileBuilder.getClassFile();
		assertEquals(COMMENT_RESULT, classFile.getCommentStr());		
	}

	@Test
	void testDeclaration() {
		ClassFile classFile = NewTestClassFileBuilder.getClassFile();
		assertEquals(DECLARATION, classFile.getDeclarationStr());		
	}
	
	@Test
	void testClassVar() {
		ClassFile classFile = NewTestClassFileBuilder.getClassFile();
		Variables vars = classFile.getClassBody().getVars();
		ClassVariable v = (ClassVariable) vars.getLine(0).get();
		assertEquals(VAR1_RESULT, v.excludeAnnotation().toString());		
	}
	
	//TODO constructor??????
	
	@Test
	void testBuildMyControls() {
//		ClassFile classFile = NewTestClassFileBuilder.getClassFile();
		ControlBuilder builder = new ControlBuilder((ElementClass) MENU_ITEM);
		
		assertEquals(ANNO_RESULT, builder.getAnnotation().toString());
	}	
	
	@Test
	void classFile() {
		ClassFile classFile = NewTestClassFileBuilder.getClassFile();
		
		System.out.println(classFile.toString()); // TODO - remove or log 	
		assertEquals(ExistingTestClassFileBuilder.CLASS_RESULT, classFile.toString());		
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
		assertEquals(
				"\t" + ANNO_RESULT + "\n" 
				+ "\tpublic static final int PANEL_TITLE = 1;\n"
				+ "\tpublic static final String MENU_TITLE = \"Employee Details\";\n"
				+ "\n"
				+ "\t" + ANNO_RESULT + "\n" 
				+ "\tpublic EmployeeDetails(CoreData coreData){\n"
				+ "\t}\n"
				+ "\n"
				+ "\t" + ANNO_RESULT + "\n" 
				+ "\tprivate String aMethod(String str, Integer idx){\n"
				+ "\t\tLine1\n"
				+ "\t\tLine2\n"
				+ "\t}", body.toString());		
	}
	
	@Test
	void classFile_fromTestClassFileBuilder() {
		ClassFile clazzFile = new ExistingTestClassFileBuilder().build();
		
		assertEquals(
				PACKAGE_RESULT +
				"\n\n" +
				IMPORT_RESULT +
				"\n" +				
				COMMENT_RESULT +
				DECLARATION +
				"\n" +
				"\t" + ANNO_RESULT + "\n" +
				"\t" + VAR1_RESULT +
				"\n" +
				"\t" + ANNO_RESULT + "\n" +
				"\t" + VAR2_RESULT +
				"\n" +
				"\t" + ANNO_RESULT + "\n" +
				"\t" + VAR3_RESULT +
				"\n\n" +
				"\t" + ANNO_RESULT + "\n" + 
				"\t" + CONSTRUCTOR_DEC +
				"\n" + CNSTR_LINES.withIndent("\t\t").toString() +
				
				
				"\t}\n\n" +
				
				"\t" + ANNO_RESULT + "\n" +
				"\t" + BUILD_MY_CONTROLS_DEC +
				"\n" + CONTROLS_LINES.withIndent("\t\t").toString() +
				"\t}\n" + 
				
				"\t" + ANNO_RESULT + "\n" +
				"\t" + NOT_FROM_SITEMAPPER_DEC +
				"\n" + NOT_FROM_SITEMAPPER_LINES.withIndent("\t\t").toString() +				
				"\t}\n"
				
				+ "}", 
				clazzFile.toString());
	}
		
//	@Test
//	void newClassFileBuilder() {
		
//		NewClassFileBuilder builder = 
//				new ClassFile.NewClassFileBuilder(menuItem);
//		
//		ClassFile classFile = builder.build();
		
//		System.out.println(classFile.toString()); // TODO - remove or log 	
//	}
	
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
