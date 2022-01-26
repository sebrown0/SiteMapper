/**
 * 
 */
package file.method;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
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

	public SiteMapAnnotation getAnnotation() {
		return annotation;
	}
		
	@Override
	public String toString() {
		return String.format(
				"%s%s %s %s(%s){\n%s\t}", 
				Formatter.getAnnotation(annotation),
				Formatter.getValueOf(modifier), 
				Formatter.getValueOf(returnType), 
				Formatter.getValueOf(name),
				Formatter.getValueOf(arguments),
				Formatter.getValueOf(body)
		);
	}
	
	/**
	 * @author SteveBrown
	 * @version 1.0
	 * 	Initial
	 * @since 1.0
	 */
	public abstract static class MethodBuilder {
		protected SiteMapAnnotation annotation;
		protected ExistingMethodBody body  = new ExistingMethodBody();
		protected int indent;
		
		/*
		 * TODO THESE SHOULD BE OBJECT DECLARATION.
		 */
		protected String modifier;
		protected String returnType;
		protected String name;
		protected ArgumentList arguments = new ArgumentList();
		//----------------------------------------------------
		
		
		public MethodBuilder(int indent) {
			this.indent = indent;
		}
		
		public MethodBuilder addLine(String line) {
			body.addLine(LineTabs.getLineWithTabs(indent, line));
			return this;
		}
		public MethodBuilder addLineWithAdditionalIndents(String line, int addIntents) {
			body.addLine(LineTabs.getLineWithTabs(indent+addIntents, line));
			return this;
		}

		public Method build() {
			return new Method(this);
		}
	}
	
	/**
	 * @author SteveBrown
	 * @version 1.0
	 * 	Initial
	 * @since 1.0
	 */
	public static class ExistingMethodBuilder extends MethodBuilder {		
		public ExistingMethodBuilder(int indent) {
			super(indent);
		}
		
		public ExistingMethodBuilder withAnnotation(String annoStr) {
			if(annoStr != null) {
				super.annotation = new ExistingAnnotation(annoStrWithoutTabs(annoStr), super.indent);//.setIndent(indent); 
			}
			return this;
		}
		
		private String annoStrWithoutTabs(String annoStr) {
			return annoStr.replace("\t", "");
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
	}

}
