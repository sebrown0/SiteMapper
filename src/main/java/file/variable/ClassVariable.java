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
public class ClassVariable extends Variable {
	public ClassVariable(VariableBuilder b) {
		super(b);	
		super.setIndent(1);	
	}

	@Override
	public String toString() {		
		return String.format(
				"%s%s%s%s%s%s%s%s;",				
				Formatter.getAnnotation(annotation),
				super.getIndent(),
				Formatter.getValueOf(modifier),
				Formatter.getValueOf(" ", staticVar),
				Formatter.getValueOf(" ", finalVar),
				Formatter.getValueOf(" ", type),				
				Formatter.getValueOf(" ", name),
				Formatter.getVariableValue(Formatter.trimValueOf(value), type));
	}

}
		

