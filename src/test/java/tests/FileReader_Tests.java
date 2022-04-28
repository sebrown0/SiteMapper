/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import utils.FileReader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class FileReader_Tests {

	@Test
	void test() {		
		Optional<String> fp = Optional.of("./src/test/java/tests/FileReader_Tests.java");
		List<String> content = FileReader.getFileAsList(fp);
		assertTrue(content.size() > 1);
	}

}
