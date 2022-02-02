/**
 * 
 */
package tests.mapping_tests;

import static helpers.ExistingTestClassBodyBuilder.VAR1_RESULT;
import static helpers.ExistingTestClassFileBuilder.DECLARATION;
import static helpers.ExistingTestClassFileBuilder.IMPORT_RESULT;
import static helpers.ExistingTestClassFileBuilder.NEW_PACKAGE;
import static helpers.ExistingTestClassFileBuilder.PACKAGE_RESULT;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import file.annotation.NewAnnotation;
import file.class_file.ClassFile;
import file.class_package.NewClassPackage;
import file.comment.NewComment;
import file.imports.ImportList;
import file.variable.ClassVariable;
import file.variable.Variables;
import helpers.ExistingTestClassFileBuilder;
import helpers.NewTestClassFileBuilder;
import site_mapper.creators.ComponentWriter;
import site_mapper.creators.ComponentWriterJsPanelWithIFrame;
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
	private static final ExistingTestClassFileBuilder FILE_BUILDER =
			new ExistingTestClassFileBuilder();
	
//	private static String ANNO_RESULT;
	private static String COMMENT_RESULT;
	
	@BeforeAll
	static void setup() {
		COMMENT_RESULT = FILE_BUILDER.COMMENT_RESULT();
//		ANNO_RESULT = FILE_BUILDER.ANNO_RESULT();
	}
	
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
				"import static org.junit.jupiter.api.Assertions.assertTrue;\n" +
				"import static org.junit.jupiter.api.Assertions.fail;\n" +
				"import java.util.List;\n" +
				"import org.openqa.selenium.By;\n" +
				"import control_builder.*;\n" +
				"import site_mapper.annotations.SiteMap;\n" +
				"import org.junit.jupiter.api.DynamicTest;\n" +
				"/* Placeholder for missing import [TestControl] */\n" +
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
		NewTestClassFileBuilder builder = new NewTestClassFileBuilder();
		ClassFile classFile = builder.getClassFile();
		assertEquals(PACKAGE_RESULT, classFile.getPackageStr());		
	}
	
	@Test
	void testImports() {
		NewTestClassFileBuilder builder = new NewTestClassFileBuilder();
		ClassFile classFile = builder.getClassFile();
		assertEquals(IMPORT_RESULT, classFile.getImportStr());		
	}
	
	@Test
	void testComment() {
		NewTestClassFileBuilder builder = new NewTestClassFileBuilder();
		ClassFile classFile = builder.getClassFile();
		assertEquals(COMMENT_RESULT, classFile.getCommentStr());		
	}

	@Test
	void testDeclaration() {
		NewTestClassFileBuilder builder = new NewTestClassFileBuilder();
		ClassFile classFile = builder.getClassFile();
		assertEquals(DECLARATION, classFile.getDeclarationStr());		
	}
	
	@Test
	void testClassVar() {
		NewTestClassFileBuilder builder = new NewTestClassFileBuilder();
		ClassFile classFile = builder.getClassFile();
		Variables vars = classFile.getClassBody().getVars();
		ClassVariable v = (ClassVariable) vars.getLine(0).get();
		assertEquals(VAR1_RESULT, v.excludeAnnotation().toString());		
	}
	
	//TODO constructor??????
	
	@Test
	void classFile() {
		NewTestClassFileBuilder builder = new NewTestClassFileBuilder();
		ClassFile classFile = builder.getClassFile();
		
		assertEquals(FILE_BUILDER.CLASS_RESULT_WITHOUT_EXTRA_METHOD(), classFile.toString());		
	}
		
}
