/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import site_mapper.creators.imports.ImportResolver;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class FindImportTests {
//	private static final String LIBRARY = 
//			"C:/Users/SteveBrown/eclipse-workspace/2021/DakarHR-Library";
	
//	private static final String ROOT = 
//			LIBRARY + "/src/main/java/library";
	
	private static List<String> required;
	private static ImportResolver impResolver;
	
	@BeforeAll
	public static void setup() {
		required = Arrays.asList(
				"TestControl", "ControlGetter", "ControlGetterGroup", 
				"JsPanelWithIFrame", "ControlData", "CoreData","CoreData", "XXXX", "bad");
		
		impResolver = 
				new ImportResolver(
						"C:/Users/SteveBrown/eclipse-workspace/2021", "SiteMapper");

		impResolver.resolveRequired(required);
	}
	
	@Test
	void test_importResolver_getGoodImp() {		
		assertEquals("core_data.CoreData", impResolver.getFoundImports().get("CoreData"));		
	}
	
	@Test
	void test_importResolver_getMissingImp() {		
		assertEquals("XXXX", impResolver.getMissing().get(0));		
	}
	
}
