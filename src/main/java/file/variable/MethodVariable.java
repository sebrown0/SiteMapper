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
	
	public MethodVariable(VariableBuilder b, int numTabs) {
		super(b);
		super.setIndent(numTabs);	
	}
	
	@Override
	public String toString() {		
		return String.format(
				"%s%s%s%s%s;", 
				Formatter.getAnnotation(annotation), 
				Formatter.getValueOf("\t", modifier),
				Formatter.getValueOf(" ", finalVar),
				Formatter.getValueOf(" ", type),				
				Formatter.getValueOf(" ", name),
				Formatter.getVariableValue(value.trim(), type));
	}

}
