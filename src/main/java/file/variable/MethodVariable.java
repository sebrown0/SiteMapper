/**
 * 
 */
package file.variable;

import file.helpers.Formatter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * POJO for class variable.
 * 
 */
public class MethodVariable extends Variable {
	private String value;
	
	public VariableSetter setValue(String value) {
		this.value = value;
		return this;
	}	

	@Override
	public String toString() {
		return String.format(
				"%s\t%s %s%s", 
				Formatter.getAnnotation(annotation), 
				Formatter.getValueOf(modifier), 
				Formatter.getValueOf(name), 
				Formatter.getVariableValue(value, modifier));
	}		
}
