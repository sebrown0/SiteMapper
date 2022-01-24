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
	private boolean includeAnnotation = true;
	
	public ClassVariable(VariableBuilder b) {
		super(b);	
		super.setIndent(1);	
	}

	@Override
	public String toString() {		
		return String.format(
				"%s%s%s%s%s%s%s%s;",				
				getAnnotation(),
				super.getIndent(),
				Formatter.getValueOf(modifier),
				Formatter.getValueOf(" ", staticVar),
				Formatter.getValueOf(" ", finalVar),
				Formatter.getValueOf(" ", type),				
				Formatter.getValueOf(" ", name),
				Formatter.getVariableValue(Formatter.trimValueOf(value), type));
	}

	public ClassVariable includeAnnotation() {
		includeAnnotation = true;
		return this;
	}
	public ClassVariable excludeAnnotation() {
		includeAnnotation = false;
		return this;
	}
	private String getAnnotation() {
		return (includeAnnotation==true) ? Formatter.getAnnotation(annotation) : "";
	}
}
		

