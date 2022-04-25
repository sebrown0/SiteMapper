/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import file.imports.Import;
import site_mapper.creators.component_writer.ComponentImports;
import site_mapper.creators.component_writer.ComponentWriterJsPanelWithIFrame;
import site_mapper.creators.imports.ImportFinder;
import site_mapper.creators.imports.ImportMatcher;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class ImportMatcherTests {
	private static ImportFinder impFinder;
	
	@BeforeAll
	public static void setup() {		
		impFinder = 
				new ImportFinder(
						"C:/Users/SteveBrown/eclipse-workspace/2021", 
						"SiteMapper",
						new ComponentImports());

		impFinder.findRequired();
	}

	@Test
	void test() {
		ImportMatcher importMatcher = new ImportMatcher(impFinder);
		ComponentWriterJsPanelWithIFrame compWriter = new ComponentWriterJsPanelWithIFrame();
		compWriter.setImportMatcher(importMatcher);
		List<Import> imports = compWriter.getImportNames();
		
		assertTrue(imports.stream().anyMatch(f -> f.getImportString().contains("CoreData")));
	}

}
