/**
 * 
 */
package file.method;

import java.util.ArrayList;
import java.util.List;

import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
import file.variable.Argument;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for a method.
 */
public class Method {
	private SiteMapAnnotation annotation;
	private String modifier = "public";
	private String returnType = "void";
	private String name;
	private List<Argument> arguments;
	private ExistingMethodBody body;
	
	public Method setAnnotation(SiteMapAnnotation annotation) {
		this.annotation = annotation;
		return this;
	}
	public Method setModifier(String modifier) {
		this.modifier = modifier;
		return this;
	}
	public Method setReturnType(String returnType) {
		this.returnType = returnType;
		return this;
	}
	public Method setName(String name) {
		this.name = name;
		return this;
	}
	public Method setVariables(List<Argument> args) {
		this.arguments = args;
		return this;
	}
	public Method addVariables(Argument arg) {
		if(arguments == null) { arguments = new ArrayList<>(); }
		arguments.add(arg);
		return this;
	}
	public Method setBody(ExistingMethodBody body) {
		this.body = body;
		return this;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s\t%s %s %s(%s){\n%s\t}", 
				Formatter.getAnnotation(annotation, "\t"), 
				Formatter.getValueOf(modifier), 
				Formatter.getValueOf(returnType), 
				Formatter.getValueOf(name),
				Formatter.getAsCommaSeparatedList(arguments),
				body.toString());
	}		
}
