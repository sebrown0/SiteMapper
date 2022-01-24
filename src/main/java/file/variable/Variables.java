/**
 * 
 */
package file.variable;

import java.util.List;
import java.util.Optional;

import file.helpers.Lines;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Variables {
	private Lines<Variable> lines = new Lines<>();
	
	public Variables setLines(List<Variable> lines) {
		this.lines.setLines(lines);
		return this;
	}
	
	public Variables addLine(Variable line) {
		lines.addLine(line);
		return this;
	}

	public List<Variable> getLines() {
		return lines.getLines();
	}
	
	public Optional<Variable> getLine(int idx) {
		return lines.getLine(idx);
	}
	
	@Override
	public String toString() {
		return lines.toString();
	}
	
}
