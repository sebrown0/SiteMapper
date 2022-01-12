/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import file.FileFinder;
import file.LineMapper;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Inital
 * @since 1.0
 *  
 */
class FileMapperTests {
	private static final String TEST_CLASS_PATH = 
			"./src/test/resources/test_data/TestClass.java";
	
	@Test
	void file_does_exist() {		
		assertTrue(FileFinder.fileExists("./src/test/java/tests", "FileMapperTests.java"));
	}
	
	@Test
	void file_does_not_exist() {		
		assertFalse(FileFinder.fileExists("./src/test/java/tests", "X_FileMapperTests.java"));
	}
	
	@Test
	public void givenFilePath_whenUsingFilesLines_thenFileData() throws URISyntaxException, IOException {	    
	    LineMapper.mapLines(TEST_CLASS_PATH).forEach(ln -> System.out.println(ln));
	}
}
