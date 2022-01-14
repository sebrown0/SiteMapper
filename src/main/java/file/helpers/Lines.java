/**
 * 
 */
package file.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Lines <T> {
	private List<T> lines = new ArrayList<>();

	public void setLines(List<T> lines) {
		if(lines != null) {
			this.lines.addAll(lines);
		}else {
			this.lines = lines;	
		}		
	}

	public Lines<T> addLine(T line) {
		lines.add(line);
		return this;
	}

	public List<T> getLines() {
		return lines;
	}
	
}
