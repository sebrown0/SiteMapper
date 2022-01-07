/**
 * 
 */
package site_mapper.creators;

import java.io.IOException;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ConstructorWriter {
	private ClassWriterActions fileOut;
	private ComponentWriter componentWriter;
	
	public ConstructorWriter(ClassWriterActions fileOut, ComponentWriter componentWriter) {
		this.fileOut = fileOut;
		this.componentWriter = componentWriter;
	}
	
	public void writeConstuctor() throws IOException {
		openConstructor();		
		writeSuperConstructor();
		writeLinesInConstructor();
		closeConstructor();
	}
	private void openConstructor() throws IOException {
		String opening = "\tpublic " + componentWriter.getClassName() + "(";
		for (String arg : componentWriter.getConstructorArgs()) {
			opening += arg + ",";
		}		
		opening = opening.substring(0, opening.length()-1) + ") {";
		fileOut.writeValue(opening);
	}
	
	private void writeSuperConstructor() throws IOException {
		List<String> args = componentWriter.getSuperArgs();
		if(args.size()>0) {
			String superCnstr = "\n\t\tsuper(";
			for (String arg : args) {
				superCnstr += arg + ",";
			}
			if(superCnstr.endsWith(",")) {
				superCnstr = superCnstr.substring(0, superCnstr.length()-1);
			}
			fileOut.writeValue(superCnstr + ");");
		}		
	}
	
	private void writeLinesInConstructor() throws IOException {
		List<String> lines = componentWriter.getConstructorLines();
		if(lines != null) {
			for (String s : lines) {
				fileOut.writeValue(s);
				fileOut.writeNewLine();	
			}	
		}		
	}

	private void closeConstructor() throws IOException {
		fileOut.writeValue("\t}");
	}
}
