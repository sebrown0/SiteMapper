/**
 * 
 */
package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Put all lines from the source file
 * into a list of lines that contain an
 * object representing the line.
 */
public class LineMapper {	
	private String filePath;
	private int lineNum;	
	private LineFactory lineFactory = new LineFactory();
	private boolean prevLineWasBlank;
	private Stage stage = new Stage();
	
	private FileLine fileLine;
	
	public LineMapper(String filePath) {
		this.filePath = filePath;
	}

	public List<Optional<FileLine>> mapLines() {
		Path path = Paths.get(filePath);
		List<Optional<FileLine>> fileLines = null;
		if(path != null) {			
			lineNum = 0;
			try {
				fileLines =	Files
						.lines(path)
						.map(ln -> getNewLine(ln))
						.collect(Collectors.toUnmodifiableList());
			} catch (IOException e) {
				// TODO Log
			}	
		}else {
			// TODO Log
		}
		return fileLines;
	}
	
	private Optional<FileLine> getNewLine(String lineData) {
		fileLine = null;

		if(isBlankLine(lineData)) {
			System.out.println("BLANK"); // TODO - remove or log 	
			fileLine = new LineBlank(lineNum);
			prevLineWasBlank = true;			
		}else {
			if(prevLineWasBlank) {
				prevLineWasBlank = false;
				stage.moveNext();
			}
			
			lineFactory
				.getFileLine(stage, lineNum, lineData)
				.ifPresent(fl -> fileLine = fl);
		}

		return Optional.ofNullable(fileLine);
	}
	
	private boolean isBlankLine(String lineData) {
		return (lineData.length()>0) ? false : true;
	}
	
}
