package tests.mapping_tests;

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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PomMapperTests {
	static final String ROOT = 
			"C:/Users/SteveBrown/eclipse-workspace/2021/SiteMapper";
	static final String XML_SOURCE = 
			ROOT + "/src/test/resources/site_map/site_map.xml";

	// This should be the same as ParentPackage in the XML file above.
	static final String PARENT_PACKAGE = 
			ROOT + "/mapped/classes"; 
			
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
		PomMapper mapper = new PomMapper(XML_SOURCE);
		mapper.createPoms();
	}
	
//	@Test @Order(3)
//	void createClassFileFromXML() {		
//		PomMapper mapper = new PomMapper(XML_SOURCE);
//		mapper.createClassFile();
//	}
	
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
