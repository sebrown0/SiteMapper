/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import file.class_file.ClassFile;
import file.existing.ExistingFileScanner;
import file.stage.InitialStage;
import file.stage.PackageStage;
import file.stage.Stage;

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
	void classVariables() {
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile clazz = scanner.getClassFile();
		
//		assertEquals(6, scanner.getClassVariables().size());
	}
	
	@Test
	void intial() {
		Stage s = new InitialStage();
		assertTrue(s instanceof InitialStage);
		assertTrue(s.getNext() instanceof PackageStage);
	}

}
