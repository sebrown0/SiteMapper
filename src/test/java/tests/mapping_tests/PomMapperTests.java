package tests.mapping_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import app.PomMapper;
import app.PomMapperVisitor;
import file.class_file.ClassFile;
import file.existing.ExistingFileScanner;
import helpers.ExistingTestClassFileBuilder;
import site_mapper.jaxb.pom.SiteMapInfo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PomMapperTests {		
	private static final String ROOT = 			
			"C:/Users/SteveBrown/eclipse-workspace/2021/SiteMapper";
	
	private static final String XML_SOURCE = 
			ROOT + "/src/test/resources/site_map/site_map.xml";

	// This should be the same as ParentPackage in the XML file above.
	private static final String PARENT_PACKAGE = 
			ROOT + "/mapped/classes"; 

	private static final String TEST_CLASS_PATH = 
			PARENT_PACKAGE + "/payroll/Left/employees/EmployeeDetails.java";
	
	private static final ExistingTestClassFileBuilder FILE_BUILDER = 
			new ExistingTestClassFileBuilder(
					"SteveBrown", 
					"1.0.0", 
					XML_SOURCE, 
					"07/01/2020", 
					"08:53:56");
	
	// Set the SiteMapInfo with the test data, i.e. date & time.
	public class PomTestData implements PomMapperVisitor {
		@Override
		public void setSiteMapInfo(SiteMapInfo info) {
			info
				.setDate("07/01/2020")
				.setTime("08:53:56");			
		}
	}
	
	@BeforeAll
	static void setup() {
		 try {
			deleteExisting();
		} catch (IOException e) {
			// Do nothing
		}
	}
	
	@Test @Order(1)
	void existingDeleted() throws NoSuchFileException{
		assertFalse(Files.exists(Paths.get(PARENT_PACKAGE)));
	}
	
	@Test @Order(2)
	void createPomsFromXML() {		
		//Existing classes deleted above. Create new.
		PomMapper mapper = new PomMapper(XML_SOURCE);		
		mapper.createTestPoms(new PomTestData());
		
		//Check the result.
		ExistingFileScanner scanner = new ExistingFileScanner();
		scanner.setScanner(TEST_CLASS_PATH);
		scanner.mapFile();
		ClassFile classFile = scanner.getClassFile();
				
		assertEquals(FILE_BUILDER.CLASS_RESULT_WITHOUT_EXTRA_METHOD(), classFile.toString());
	}
		
	@Test @Order(4)
	void filesCreated() throws NoSuchFileException{
		assertTrue(Files.exists(Paths.get(PARENT_PACKAGE)));
	}
	
	// HELPERS
	private static void deleteExisting() throws IOException {
		Path p = Paths.get(PARENT_PACKAGE);
		try(Stream<Path> walk = Files.walk(p)){
			walk
				.sorted(Comparator.reverseOrder())
				.forEach(d -> deleteDirectory(d));
		}
	}
	private static void deleteDirectory(Path path) {
    try {
        Files.delete(path);
    } catch (IOException e) {
        System.err.printf("Unable to delete this path : %s%n%s", path, e);
    }
}
}
