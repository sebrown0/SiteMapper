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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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
//	@Test
//	void jjkjkjkjkjk() throws IOException {
//		FileReader<FileMapperTests> reader = new FileReader<FileMapperTests>(FileMapperTests.class);
////		Class<FileMapperTests> c = FileMapperTests.class;
//		String s = reader.getInputStreamFromResources("/fileTest.txt").readFromInputStream();
//		System.out.print(s);
//	}
	@Test
	public void givenFilePath_whenUsingFilesLines_thenFileData() throws URISyntaxException, IOException {
	    String expectedData = "Hello, world!";
	      
	    Path path = Paths.get("./src/main/resources/fileTest.txt");
//	    Path path = Paths.get(getClass().getClassLoader()
//	      .getResource("fileTest.txt").toURI());
	         
	    List<String> lines = Files.lines(path).collect(Collectors.toList());
	    lines.forEach(ln -> System.out.println(ln));
//	    String data = lines.collect(Collectors.joining("\n"));
//	    lines.close();
	         
//	    assertEquals(expectedData, data.trim());
	}
}
