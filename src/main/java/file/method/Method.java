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
				"%s%s %s %s(%s){\n%s\t}", 
				Formatter.getAnnotation(annotation),
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
			body.addLine(LineTabs.getLineWithTabs(indent+1, line));
			return this;
		}

		public Method build() {
			return new Method(this);
		}
	}

//	public static class NewMethodBuilder extends MethodBuilder {		
//		private SiteMapInfo siteInfo;
//		private Element element;
//		
//		
//		public NewMethodBuilder(int indent, SiteMapInfo siteInfo, Element element) {
//			super(indent);
//			
//			this.siteInfo = siteInfo;
//			this.element = element;
//		}
//		
//		public MethodBuilder includeAnnotation() {
//			super.annotation = new NewAnnotation(siteInfo, indent);
//			return this;
//		}
//		
////	.withDeclarationStr("private String aMethod(String str, Integer idx)")
////	.addLine("Line1")
////	.addLine("Line2")
//
//	}
	
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
				super.annotation = new ExistingAnnotation(annoStr, super.indent);//.setIndent(indent); 
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
				
	}

}
