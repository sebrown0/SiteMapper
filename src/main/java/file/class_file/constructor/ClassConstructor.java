/**
 * 
 */
package file.class_file.constructor;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
import file.helpers.Lines;
import file.method.MethodDeclarationMapper;
import file.variable.ArgumentList;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ClassConstructor {
	private SiteMapAnnotation cnstrAnnotation;
	private String modifier;
	private String className;
	private ArgumentList argList;
	private Lines<String> lines;

	private ClassConstructor(ConstructorBuilder b) {
		this.cnstrAnnotation = b.cnstrAnnotation;
		this.modifier = b.modifier;
		this.className = b.className;
		this.argList = b.argList;
		this.lines = b.lines;
	}
	
	@Override
	public String toString() {		
		return String.format(
			"%s%s%s(%s){\n%s\t}",
			Formatter.getAnnotation("\t", cnstrAnnotation),
			Formatter.getValueOf("\t", modifier),
			Formatter.getValueOf(" ", className), 
			Formatter.getValueOf(argList.toString()),
			lines.toString()
		);
	}
		
	/**
	 * 
	 * @author SteveBrown
	 * @version 1.0
	 * 	Initial
	 * @since 1.0
	 */
	public abstract static class ConstructorBuilder {
		private SiteMapAnnotation cnstrAnnotation;
		private String modifier;
		private String className;
		private ArgumentList argList;
		private Lines<String> lines = new Lines<>();
		
		protected abstract ClassConstructor build();
		protected abstract ConstructorBuilder withAnnotation(String a);
				
		public void setCnstrAnnotation(SiteMapAnnotation cnstrAnnotation) {
			this.cnstrAnnotation = cnstrAnnotation;
		}
		public void setModifier(String modifier) {
			this.modifier = modifier;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public void setArguments(ArgumentList arguments) {
			this.argList = arguments;
		}
		public void addLine(String line) {
			this.lines.addLine(line);
		}
	}
	
	/**
	 * 
	 * @author SteveBrown
	 * @version 1.0
	 * 	Initial
	 * @since 1.0
	 */
	public static class ExistingConstructorBuilder extends ConstructorBuilder {
		
		@Override
		public ExistingConstructorBuilder withAnnotation(String annoStr) {
			if(annoStr != null) {
				super.cnstrAnnotation = new ExistingAnnotation(annoStr);
			}
			return this;
		}

		public ExistingConstructorBuilder withConstructorDeclaration(String cnstrStr) {
			MethodDeclarationMapper mapper = new MethodDeclarationMapper();
			mapper.mapDeclaration(cnstrStr);
			super.modifier = mapper.getModifier().toString();
			super.className = mapper.getName();
			super.argList = mapper.getArgs();

			return this;
		}
		
		@Override
		protected ClassConstructor build() {
			return new ClassConstructor(this);
		}


	}

	
}
