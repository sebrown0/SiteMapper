/**
 * 
 */
package file.method;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
import file.helpers.IndentedElement;
import file.helpers.LineTabs;
import file.variable.ArgumentList;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for a method.
 */
public class Method implements IndentedElement<Method> {
	private SiteMapAnnotation annotation;
	private String modifier = "public";
	private String returnType = "void";
	private String name;
	private ArgumentList arguments = new ArgumentList();
	private ExistingMethodBody body;
	private int numTabs;
	
	private Method(MethodBuilder b) {
		this.annotation = b.annotation;
		this.modifier = b.modifier;
		this.returnType = b.returnType;
		this.name = b.name;
		this.arguments = b.arguments;
		this.body = b.body;
		this.numTabs = b.indent;
	}

	@Override //IndentedElement
	public Method setIndent(int numTabs) {
		this.numTabs = numTabs;
		return this;
	}
		
	@Override
	public String toString() {
		return String.format(
				"%s%s%s %s %s(%s){\n%s\t}", 
				Formatter.getAnnotation(annotation),
				getIndent(),
				Formatter.getValueOf(modifier), 
				Formatter.getValueOf(returnType), 
				Formatter.getValueOf(name),
				Formatter.getValueOf(arguments),
				Formatter.getValueOf(body)
		);
	}

	public String getIndent() {
		return LineTabs.getTabStr(numTabs);
	}
	
	/**
	 * @author SteveBrown
	 * @version 1.0
	 * 	Initial
	 * @since 1.0
	 */
	public abstract static class MethodBuilder {
		protected SiteMapAnnotation annotation;
		/*
		 * TODO THESE SHOULD BE OBJECT DECLARATION.
		 */
		protected String modifier;
		protected String returnType;
		protected String name;
		protected ArgumentList arguments = new ArgumentList();
		//----------------------------------------------------
		protected ExistingMethodBody body  = new ExistingMethodBody();
		private int indent;
		
		public abstract Method build();
		protected abstract MethodBuilder withAnnotation(String a);
		
		public MethodBuilder addLine(String line) {
			body.addLine(LineTabs.getLineWithTabs(indent+2, line));
			return this;
		}
	}
	
	/**
	 * @author SteveBrown
	 * @version 1.0
	 * 	Initial
	 * @since 1.0
	 */
	public static class ExistingMethodBuilder extends MethodBuilder {
		private int indent;
		
		public ExistingMethodBuilder(int indent) {
			this.indent = indent;
		}

		@Override
		public ExistingMethodBuilder withAnnotation(String annoStr) {
			if(annoStr != null) {
				super.annotation = new ExistingAnnotation(annoStr, super.indent).setIndent(indent); 
			}
			return this;
		}
		
		public ExistingMethodBuilder withDeclarationStr(String cnstrStr) {
			MethodDeclarationMapper mapper = new MethodDeclarationMapper();
			mapper.mapDeclaration(cnstrStr);
			super.modifier = LineTabs.getLineWithTabs(indent, mapper.getModifier().toString());
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
