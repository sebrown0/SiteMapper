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
public abstract class Import <T> {
	protected List<T> lines = new ArrayList<>();

	public Import<T> addLine(T line) {
		lines.add(line);
		return this;
	}
	
}
