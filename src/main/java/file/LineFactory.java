/**
 * 
 */
package file;

import java.util.Optional;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class LineFactory {

	public Optional<FileLine> getFileLine(Stage stages, int lineNum, String value) {
		FileLine fileLine = null;
		System.out.println("->" + stages.peekCurrent()); // TODO - remove or log 	
		switch (stages.peekCurrent()) {
			case INITIAL, PACKAGE -> stages.moveNext();
			case IMPORTS -> fileLine = new LineImport(lineNum, value);
			case COMMENTS -> fileLine = new LineImport(lineNum, value);
			case OPEN_CLASS -> fileLine = new LineImport(lineNum, value);
			case VARIABLES -> fileLine = new LineImport(lineNum, value);
			case CONSTRUCTOR -> fileLine = new LineImport(lineNum, value);
			case BODY -> fileLine = new LineImport(lineNum, value);
		
			default ->
				throw new IllegalArgumentException("Unexpected value: " + stages.peekCurrent());
		}
		return Optional.ofNullable(fileLine);
	}
	
	
}
