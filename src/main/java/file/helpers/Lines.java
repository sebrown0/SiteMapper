/**
 * 
 */
package file.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	public Lines<T> setLines(List<T> lines) {
		if(lines != null) {
			this.lines.addAll(lines);
		}else {
			this.lines = lines;	
		}		
		return this;
	}

	public Lines<T> addLine(T line) {
		lines.add(line);
		return this;
	}

	public Optional<T> getLine(int idx) {
		Optional<T> res = Optional.ofNullable(null);
		if(idx >= 0 && idx <= lines.size()) {
			res = Optional.of(lines.get(idx));
		}
		return res;
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
