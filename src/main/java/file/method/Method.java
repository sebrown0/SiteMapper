/**
 * 
 */
package file.method;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
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
	
	private Method(MethodBuilder b) {
		this.annotation = b.annotation;
		this.modifier = b.modifier;
		this.returnType = b.returnType;
		this.name = b.name;
		this.arguments = b.arguments;
		this.body = b.body;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s\t%s %s %s(%s){\n%s\t}", 
				Formatter.getAnnotation(annotation, "\t"), 
				Formatter.getValueOf(modifier), 
				Formatter.getValueOf(returnType), 
				Formatter.getValueOf(name),
				Formatter.getValueOf(arguments),
				Formatter.getValueOf(body)
		);
	}
	
	/*
	 * MethodDeclarationMapper
	 */
	public abstract static class MethodBuilder {
		protected SiteMapAnnotation annotation;
		protected String modifier = "public";
		protected String returnType = "void";
		protected String name;
		protected ArgumentList arguments = new ArgumentList();
		protected ExistingMethodBody body = new ExistingMethodBody();
		
		public abstract Method build();
		protected abstract MethodBuilder withAnnotation(String a);

		public MethodBuilder addLine(String line) {
			body.addLine(line);
			return this;
		}
	}
	
	public static class ExistingMethodBuilder extends MethodBuilder {

		@Override
		public ExistingMethodBuilder withAnnotation(String annoStr) {
			if(annoStr != null) {
				super.annotation = new ExistingAnnotation(annoStr);
			}
			return this;
		}
		
		public ExistingMethodBuilder withDeclarationStr(String cnstrStr) {
			MethodDeclarationMapper mapper = new MethodDeclarationMapper();
			mapper.mapDeclaration(cnstrStr);
			super.modifier = mapper.getModifier().toString();
			super.returnType = mapper.getReturnType();
			super.name = mapper.getName();
			super.arguments = mapper.getArgs();

			return this;
		}
		
		@Override
		public Method build() {
			return new Method(this);
		}
		
	}
}
