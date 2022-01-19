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
	
	public MethodVariable(VariableBuilder b) {
		super(b);
	}
	
	@Override
	public String toString() {		
		return String.format(
				"%s%s%s%s%s;", 
				Formatter.getAnnotation(annotation, "\t"), 
				Formatter.getValueOf("\t", modifier),
				Formatter.getValueOf(" ", finalVar),
				Formatter.getValueOf(" ", type),				
				Formatter.getValueOf(" ", name),
				Formatter.getVariableValue(value.trim(), type));
	}

}
