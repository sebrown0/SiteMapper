/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import utils.FileFinder;
import utils.FileReader;

/**
 * @author Brown
 *
 */
class FileMapperTests {

	@Test
	void file_does_exist() {		
		assertTrue(FileFinder.fileExists("./src/test/java/tests", "FileMapperTests.java"));
	}
	@Test
	void file_does_not_exist() {		
		assertFalse(FileFinder.fileExists("./src/test/java/tests", "X_FileMapperTests.java"));
	}
	@Test
	void jjkjkjkjkjk() throws IOException {
		FileReader<FileMapperTests> reader = new FileReader<FileMapperTests>(FileMapperTests.class);
//		Class<FileMapperTests> c = FileMapperTests.class;
		String s = reader.getInputStreamFromResources("/fileTest.txt").readFromInputStream();
		System.out.print(s);
	}
	@Test
	public void givenFilePath_whenUsingFilesLines_thenFileData() throws URISyntaxException, IOException {
	    String expectedData = "Hello, world!";
	      
	    Path path = Paths.get("./src/main/resources/fileTest.txt");
//	    Path path = Paths.get(getClass().getClassLoader()
//	      .getResource("fileTest.txt").toURI());
	         
	    Stream<String> lines = Files.lines(path);
	    String data = lines.collect(Collectors.joining("\n"));
	    lines.close();
	         
	    assertEquals(expectedData, data.trim());
	}
}
