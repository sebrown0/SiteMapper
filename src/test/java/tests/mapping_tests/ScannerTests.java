/**
 * 
 */
package tests.mapping_tests;

import static helpers.ExistingTestClassBodyBuilder.BODY_RESULT_WITH_EXTRA_METHOD;
import static helpers.ExistingTestClassFileBuilder.CLASS_RESULT_FULL;
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
import file.stage.InitialStage;
import file.stage.PackageStage;
import file.stage.Stage;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class ScannerTests {
	private static final String TEST_CLASS_PATH = 
			"./src/test/resources/test_data/TestClass.java";

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
		ClassBody body = scanner.getClassFile().getClassBody();
		MethodList methods = body.getMethods();

		assertEquals(
				"\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")\n"
				+ "\tprivate void buildMyControls(){\n"
				+ "\t\tvar myControls =\n"
				+ "\t\t\tList.of(\n"
				+ "\t\t\t\tnew ControlData(\"save\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='SAVE']\"))),\n"
				+ "\t\t\t\tnew ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF1']\"))),\n"
				+ "\t\t\t\tnew ControlData(\"code\", new ControlGetterTextOut(coreData, By.cssSelector(\"input[id='FORM_ID']\")))\n"
				+ "\t\t\t);\n"
				+ "\t\tsuper.buildPanelControls(myControls);\n"
				+ "\t}\n"
				+ "\tprivate String aMethodNotFromSiteMapper(int idx){\n"
				+ "\t\tString aStr = \"\";\n"
				+ "\t\t//do some stuff...\n"
				+ "\t\t\n"
				+ "\t\treturn aStr;\n"
				+ "\t}", 
				methods.toString()
		);
	}
	
	@Test
	void classBody() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassBody body = scanner.getClassFile().getClassBody();
				
		assertEquals(BODY_RESULT_WITH_EXTRA_METHOD, body.toString());
	}
	
	@Test
	void classFile() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile classFile = scanner.getClassFile();
				
		assertEquals(CLASS_RESULT_FULL, classFile.toString());
	}
	
	@Test
	void intial() {
		//TODO - DO WE NEED Stage???? 
		Stage s = new InitialStage();
		assertTrue(s instanceof InitialStage);
		assertTrue(s.getNext() instanceof PackageStage);
	}

}
