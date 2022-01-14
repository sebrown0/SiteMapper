/**
 * 
 */
package file.class_file;

import java.util.List;

import file.annotation.SiteMapAnnotation;
import file.variable.Argument;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class ClassConstructor {
	protected SiteMapAnnotation cnstrAnnotation;
	private String modifier;
	private String className;
	protected List<Argument> arguments;
	
//	public ClassConstructor(String modifier, String className) {
//		this.modifier = modifier;
//		this.className = className;
//	}
//	
//	

	@Override
	public String toString() {		
		return String.format("%s\t%s %s(){\n\t}", getAnnotation(), modifier, className);
	}
	
	private String getAnnotation() {
		return (cnstrAnnotation != null) ? "\t" + cnstrAnnotation + "\n" : "";
	}
}
