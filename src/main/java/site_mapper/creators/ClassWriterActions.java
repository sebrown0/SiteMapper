/**
 * 
 */
package site_mapper.creators;

import java.io.IOException;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ClassWriterActions {
	void writeValue(String value) throws IOException;
	void writeLine(String value) throws IOException;
	void writeNewLines(int numLines) throws IOException;
	void writeNewLine() throws IOException;
	void writeAnnotation() throws IOException;
	void addTab() throws IOException;		
}
