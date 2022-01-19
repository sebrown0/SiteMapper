/**
 * 
 */
package file.method;

import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
import file.variable.Argument;
import file.variable.ArgumentList;

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
	private ArgumentList arguments = new ArgumentList();
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
	public Method setVariables(ArgumentList args) {
		this.arguments = args;
		return this;
	}
	public Method addVariables(Argument arg) {
		arguments.addArg(arg);
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
				arguments.toString(),
				body.toString());
	}
	
	private abstract static class MethodBuilder {
		protected SiteMapAnnotation annotation;
		protected String modifier = "public";
		protected String returnType = "void";
		protected String name;
		protected ArgumentList arguments = new ArgumentList();
		protected ExistingMethodBody body;
		
		protected abstract Method build();
	}
	
	public static class ExistingMethodBuilder extends MethodBuilder {

		@Override
		protected Method build() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
