/**
 * 
 */
package file.variable;

import file.helpers.Formatter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for a method argument variable.
 * 
 */
public class Argument {
	private String type;
	private String name;
	
	public Argument(String type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format(
				"%s %s",				 
				Formatter.getValueOf(type), 
				Formatter.getValueOf(name));
	}		
}
