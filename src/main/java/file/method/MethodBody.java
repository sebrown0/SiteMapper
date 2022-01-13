/**
 * 
 */
package file.method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * All the lines of a method body as supplied.
 */
public class MethodBody {
	private List<String> lines = new ArrayList<>();

	public void setLines(List<String> lines) {
		if(lines != null) {
			this.lines.addAll(lines);
		}else {
			this.lines = lines;	
		}		
	}

	public MethodBody addLine(String line) {
		lines.add(line);
		return this;
	}
	
	@Override
	public String toString() {
		String body = "";
		for (String s : lines) {
			body += "\t\t" + s + "\n";
		}
		return body;
	}	
	
}
