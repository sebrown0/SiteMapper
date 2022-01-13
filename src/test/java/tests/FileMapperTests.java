/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import file.FileFinder;
import file.FileLine;
import file.LineFactory;
import file.LineImport;
import file.LineMapper;
import file.Stage;
import file.Stage.FileStages;

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
	public void mapLines_inTestClass() {
		LineMapper lineMapper = new LineMapper(TEST_CLASS_PATH);				
		assertTrue(lineMapper.mapLines().size() > 0);
	}
	
	@Test
	void fileStage_initial() {
		Stage stage = new Stage();
		assertEquals(FileStages.INITIAL, stage.peekCurrent());		
	}
	@Test
	void fileStage_moveNext() {
		Stage stage = new Stage();
		assertEquals(FileStages.PACKAGE, stage.moveNext());		
	}
	@Test
	void fileStage_set() {
		Stage stage = new Stage();		
		stage.setStage(FileStages.IMPORTS);
		assertEquals(FileStages.IMPORTS, stage.peekCurrent());		
	}
	
	@Test
	void lineFactory() {
		Optional<FileLine> fileLine;
		Stage stage = new Stage();		
		assertEquals(FileStages.INITIAL, stage.peekCurrent());
		
		LineFactory lineFactory = new LineFactory();
		
		fileLine = lineFactory.getFileLine(stage, 0, "line data");
		assertTrue(fileLine.isEmpty());
		assertEquals(FileStages.IMPORTS, stage.moveNext());
		
		fileLine = lineFactory.getFileLine(stage, 0, "line data");
		assertTrue(fileLine.get() instanceof LineImport);
		assertEquals(FileStages.COMMENTS, stage.moveNext());
		
	}
}
