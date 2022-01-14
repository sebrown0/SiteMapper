/**
 * 
 */
package file.method;

import java.util.List;

import file.helpers.Lines;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * All the lines of a method body as supplied.
 */
public class ExistingMethodBody {
	private Lines<String> lines = new Lines<>();
	
	public void setLines(List<String> lines) {
		this.lines.setLines(lines);
	}
	
	public ExistingMethodBody addLine(String line) {
		lines.addLine(line);
		return this;
	}
	
	
	@Override
	public String toString() {
		String body = "";
		for (String s : lines.getLines()) {
			body += "\t\t" + s + "\n";
		}
		return body;
	}	
	
}
