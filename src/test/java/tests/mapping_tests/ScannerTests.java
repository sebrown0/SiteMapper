/**
 * 
 */
package tests.mapping_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import file.class_file.ClassFile;
import file.class_file.body.ClassBody;
import file.class_file.constructor.ClassConstructor;
import file.existing.ExistingFileScanner;
import file.helpers.LineTabs;
import file.method.MethodList;
import file.modifier.Modifier;
import helpers.ExistingTestClassBodyBuilder;
import helpers.ExistingTestClassFileBuilder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class ScannerTests {
	private static final String TEST_CLASS_PATH = 
			"./src/test/resources/test_data/TestClass.java";

	private static final ExistingTestClassFileBuilder FILE_BUILDER = new ExistingTestClassFileBuilder();
	
	@Test
	void numTabs() {
		final String TABS = "\t\t\t";
		assertTrue(new LineTabs(TABS).getNumTabs() == 3);
	}
	@Test
	void tabString() {
		final String STR = "a string";
		new LineTabs();
		assertEquals("\t\t\t" + STR, LineTabs.getLineWithTabs(3, STR));
	}
	@Test
	void justTabs() {		
		assertEquals("\t\t\t", LineTabs.getTabStr(3));		
	}
	
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
		ClassFile clazz = scanner.getClassFile().get();
		
		assertEquals("package employees;", clazz.getPackageStr());
	}

	@Test
	void classImports() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile clazz = scanner.getClassFile().get();
		
		assertEquals(10, clazz.getImport().getImports().size());
	}
	
	@Test
	void classComment() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile clazz = scanner.getClassFile().get();

		assertEquals(8, clazz.getComment().getLines().size());
	}
	
	@Test
	void classDeclaration() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile clazz = scanner.getClassFile().get();
		
		assertEquals("public class EmployeeDetails extends JsPanelWithIFrame {", clazz.getDeclarationStr());
	}
	
	@Test
	void classBody_classVars() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassBody body = scanner.getClassFile().get().getClassBody();
		
		assertEquals(3, body.getVars().getLines().size());
	}
	
	@Test
	void classBody_classConstructor() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassBody body = scanner.getClassFile().get().getClassBody();
		ClassConstructor cnstr = body.getCnstr();

		assertEquals(
			"\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")\n" +
			"\tpublic EmployeeDetails(CoreData coreData){\n" +
			"\t\tsuper(coreData, PANEL_TITLE);\n" +
			"\t\tbuildMyControls();\n" +
			"\t}", 
			cnstr.toString());
	}
	
	@Test
	void classBody_classMethods() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassBody body = scanner.getClassFile().get().getClassBody();
		MethodList methods = body.getMethods();

		assertEquals(
				"\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")\n"
				+ "\tprivate void buildMyControls() {\n"
				+ "\t\tvar myControls =\n"
				+ "\t\t\tList.of(\n"
				+ "\t\t\t\tnew ControlData(\"save\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='SAVE']\"))),\n"
				+ "\t\t\t\tnew ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF1']\"))),\n"
				+ "\t\t\t\tnew ControlData(\"code\", new ControlGetterTextOut(coreData, By.cssSelector(\"input[id='FORM_ID']\")))\n"
				+ "\t\t\t);\n"
				+ "\t\tsuper.buildPanelControls(myControls);\n"
				+ "\t}\n"
				+ "\tprivate String aMethodNotFromSiteMapper(int idx) {\n"
				+ "\t\tString aStr = \"\";\n"
				+ "\t\t//do some stuff...\n"
				+ "\t\t\n"
				+ "\t\treturn aStr;\n"
				+ "\t}\n"
				+ "\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")\n"
				+ "\t@TestControl(type=\"button\")\n"
				+ "\tpublic DynamicTest buttonSave () {\n"
				+ "\t\treturn DynamicTest.dynamicTest(\"[buttonSave] *NOT IMPLEMENTED*\", () -> assertTrue(true));\n"
				+ "\t}\n"
				+ "\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")\n"
				+ "\t@TestControl(type=\"button\")\n"
				+ "\tpublic DynamicTest buttonSearch () {\n"
				+ "\t\treturn DynamicTest.dynamicTest(\"[buttonSearch]\", () -> fail(\"*NOT IMPLEMENTED*\"));\n"
				+ "\t}", 
				methods.toString()
		);
	}
	
	@Test
	void classBody() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassBody body = scanner.getClassFile().get().getClassBody();
		
		final ExistingTestClassBodyBuilder builder = new ExistingTestClassBodyBuilder(FILE_BUILDER);
		assertEquals(builder.BODY_RESULT_WITH_TEST_METHODS_AND_EXTRA_METHOD(), body.toString());
	}
	
	@Test
	void classFile() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile classFile = scanner.getClassFile().get();
						
		assertEquals(FILE_BUILDER.CLASS_RESULT_FULL(), classFile.toString());
	}
	

}
