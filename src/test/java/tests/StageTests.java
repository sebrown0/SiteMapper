/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import file.class_file.ClassBody;
import file.class_file.ClassFile;
import file.class_file.constructor.ClassConstructor;
import file.existing.ExistingFileScanner;
import file.method.MethodList;
import file.modifier.Modifier;
import file.stage.InitialStage;
import file.stage.PackageStage;
import file.stage.Stage;
import site_mapper.annotations.SiteMap;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class StageTests {
	private static final String TEST_CLASS_PATH = 
			"./src/test/resources/test_data/TestClass.java";

	@Test
	void startsWithModifierPattern() {
		assertTrue(Modifier.startsWithValidModifier("zpublic blah blah"));
		assertFalse(Modifier.startsWithValidModifier("\tpublicblah blah"));
		assertTrue(Modifier.startsWithValidModifier("\tpublic blah blah"));
		assertTrue(Modifier.startsWithValidModifier("public blah blah"));
	}
	@Test
	void annotationPattern() {
		Pattern pattern = Pattern.compile(".*@SiteMap.*");
		assertTrue(pattern.matcher("\t@SiteMap").find());
		assertFalse(pattern.matcher("\tSiteMap").find());
	}
	
	@Test
	void modifierPattern() {
		Pattern pattern = Pattern.compile(".*public.*|.*protected.*|.*private.*");

		assertTrue(pattern.matcher("\t\t\tpublic").find());
		assertTrue(pattern.matcher("\t\t\tprotected").find());
		assertTrue(pattern.matcher("\t\t\tprivate").find());
		assertFalse(pattern.matcher("\t\t\tpUblic").find());
		assertFalse(pattern.matcher("\t\t\tprOtected").find());
		assertFalse(pattern.matcher("\t\t\tpriVate").find());
	}
	
	@Test
	void classPackage() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile clazz = scanner.getClassFile();
		
		assertEquals("package a.payroll.Left.employees;", clazz.getPackageStr());
	}

	@Test
	void classImports() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile clazz = scanner.getClassFile();
		
		assertEquals(6, clazz.getImport().getImports().size());
	}
	
	@Test
	void classComment() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile clazz = scanner.getClassFile();

		assertEquals(8, clazz.getComment().getLines().size());
	}
	
	@Test
	void classDeclaration() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile clazz = scanner.getClassFile();
		
		assertEquals("public class EmployeeDetails extends JsPanelWithIFrame {", clazz.getDeclarationStr());
	}
	
	@Test
	void classBody_classVars() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassBody body = scanner.getClassFile().getClassBody();
		
		assertEquals(3, body.getVars().getLines().size());
	}
	
	@Test
	void classBody_classConstructor() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassBody body = scanner.getClassFile().getClassBody();
		ClassConstructor cnstr = body.getCnstr();

		assertEquals(
			"\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")\n" +
			"\tpublic EmployeeDetails(CoreData coreData){\n" +
			"\t\tsuper(coreData,PANEL_TITLE);\n" +
			"\t\tbuildMyControls();\n" +
			"\t}", 
			cnstr.toString());
	}
	
	@Test
	void classBody_classMethods() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassBody body = scanner.getClassFile().getClassBody();
		MethodList methods = body.getMethods();
		System.out.println(methods.toString() ); // TODO - remove or log 	
		assertEquals(
				"\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")\r\n"
				+ "\tprivate void buildMyControls() {\r\n"
				+ "\t\tvar myControls = \r\n"
				+ "\t\t\tList.of(\r\n"
				+ "\t\t\t\tnew ControlData(\"save\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='SAVE']\"))),\r\n"
				+ "\t\t\t\tnew ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF1']\"))),\r\n"
				+ "\t\t\t\tnew ControlData(\"code\", new ControlGetterTextOut(coreData, By.cssSelector(\"input[id='FORM_ID']\")))\r\n"
				+ "\t\t\t);\r\n"
				+ "\t\tsuper.buildPanelControls(myControls);\r\n"
				+ "\t}\r\n"
				+ "\tprivate String aMethodNotFromSiteMapper(int idx){\r\n"
				+ "\t\tString aStr = \"\";\r\n"
				+ "\t\t//do some stuff...\r\n"
				+ "\t\t\r\n"
				+ "\t\treturn aStr;\r\n"
				+ "\t}", 
				methods.toString()
		);
	}
	
	@Test
	void intial() {
		Stage s = new InitialStage();
		assertTrue(s instanceof InitialStage);
		assertTrue(s.getNext() instanceof PackageStage);
	}

}
