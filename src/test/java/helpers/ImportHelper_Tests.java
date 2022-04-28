/**
 * 
 */
package helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import utils.ImportHelper;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class ImportHelper_Tests {

	@Test
	void test_classFromMalFormedImportString() {
		assertTrue(ImportHelper.getClassFromImportStr("AMalFormedImport;")==null);
	}
	
	@Test
	void test_classFromImportString_withNoPackge() {
		assertEquals("AnImport", ImportHelper.getClassFromImportStr("import AnImport;"));
	}
	
	@Test
	void test_classFromImportString_withPackge() {
		assertEquals("AnImport", ImportHelper.getClassFromImportStr("import from.some.where.AnImport;"));
	}

}
