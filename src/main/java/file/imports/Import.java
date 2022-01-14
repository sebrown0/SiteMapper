/**
 * 
 */
package file.imports;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class Import {
	protected List<String> lines = new ArrayList<>();

	public void setLines(List<String> lines) {
		if(lines != null) {
			this.lines.addAll(lines);
		}else {
			this.lines = lines;	
		}		
	}

	public Import addLine(String line) {
		lines.add(line);
		return this;
	}
	
}
