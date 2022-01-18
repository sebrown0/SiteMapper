/**
 * 
 */
package file.variable;

import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
import file.method.Method;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * POJO for class variable.
 * 
 */
public class MethodVariable extends Variable {
	private SiteMapAnnotation annotation;	
	
	@Override
	public String toString() {		
		return String.format(
				"%s%s%s%s%s;", 
				Formatter.getAnnotation(annotation, "\t"), 
				Formatter.getValueOf("\t", modifier),
//				Formatter.getValueOf(" ", staticVar),
//				Formatter.getValueOf(" ", finalVar),
				Formatter.getValueOf(" ", type),				
				Formatter.getValueOf(" ", name),
				Formatter.getVariableValue(value.trim(), type));
	}

	public MethodVariable setAnnotation(SiteMapAnnotation a) {
		this.annotation = a;
		return this;
	}
	
//	private String value;
//	
//	public VariableSetter setValue(String value) {
//		super.value = value;
//		return this;
//	}	

//	@Override
//	public String toString() {
//		return String.format(
//				"%s\t%s %s%s", 
//				Formatter.getAnnotation(annotation), 
//				Formatter.getValueOf(modifier), 
//				Formatter.getValueOf(name), 
//				Formatter.getVariableValue(value, modifier));
//	}

}
