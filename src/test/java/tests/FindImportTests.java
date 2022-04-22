/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import site_mapper.creators.imports.ImportFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class FindImportTests {	
	private static List<String> required;
	private static ImportFinder impFinder;
	
	@BeforeAll
	public static void setup() {
		required = Arrays.asList(
				"TestControl", "ControlGetter", "ControlGetterGroup", 
				"JsPanelWithIFrame", "ControlData", "CoreData","CoreData", "XXXX", "bad");
		
		impFinder = 
				new ImportFinder(
						"C:/Users/SteveBrown/eclipse-workspace/2021", 
						"SiteMapper",
						required);

		impFinder.findRequired();
	}
	
	@Test
	void test_importResolver_getGoodImp() {		
		assertEquals("core_data.CoreData", impFinder.getResolvedImports().get("CoreData"));		
	}
	
	@Test
	void test_importResolver_getMissingImp() {		
		assertEquals("XXXX", impFinder.getMissing().get(0));		
	}
	
}
