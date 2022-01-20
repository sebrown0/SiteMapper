/**
 * 
 */
package file.comment;

import java.util.List;

import file.helpers.Lines;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class Comment {
	protected Lines<String> lines = new Lines<>();
	
	public void setLines(List<String> lines) {
		this.lines.setLines(lines);
	}
	
	public Comment addLine(String line) {
		lines.addLine(line);
		return this;
	}

	public List<String> getLines() {
		return lines.getLines();
	}
		
}
