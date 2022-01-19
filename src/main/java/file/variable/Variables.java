/**
 * 
 */
package file.variable;

import java.util.List;

import file.helpers.Lines;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Variables {
	Lines<Variable> lines = new Lines<>();
	
	public void setLines(List<Variable> lines) {
		this.lines.setLines(lines);
	}
	
	public Variables addLine(Variable line) {
		lines.addLine(line);
		return this;
	}

	public List<Variable> getLines() {
		return lines.getLines();
	}
		
}
