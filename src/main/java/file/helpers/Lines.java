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
	private String indent = "";
	
	public Lines<T> withIndent(String indent) {
		this.indent = indent;
		return this;
	}
	
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
	
	@Override
	public String toString() {
		String res = "";		
		for (T t : lines) {
			res += indent + t.toString() + "\n";
		}
		return res;
	}

}
