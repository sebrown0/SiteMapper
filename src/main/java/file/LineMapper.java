/**
 * 
 */
package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
	private static int lineNum;	
	
	public static List<FileLine> mapLines(String filePath) {
		Path path = Paths.get(filePath);
		List<FileLine> lines = null;
		if(path != null) {			
			lineNum = 0;
			try {
				lines =	Files
						.lines(path)
						.map(ln -> new FileLine(++lineNum, ln))
						.collect(Collectors.toUnmodifiableList());
			} catch (IOException e) {
				// TODO Log
			}	
		}else {
			// TODO Log
		}
		return lines;
	}
	
}
