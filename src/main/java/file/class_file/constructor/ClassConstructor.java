/**
 * 
 */
package file.class_file.constructor;

import java.util.Scanner;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
import file.helpers.Lines;
import file.variable.ArgumentList;
import file.variable.Variables;

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
		private Scanner scanner;	
		
		public ExistingConstructorBuilder(Scanner scanner) {
			this.scanner = scanner;
		}
		
		@Override
		public ExistingConstructorBuilder withAnnotation(String annoStr) {
			if(annoStr != null) {
				super.cnstrAnnotation = new ExistingAnnotation(annoStr);
			}
			return this;
		}
		public ExistingConstructorBuilder withConstructorStr(String cnstrStr) {
			if(cnstrStr != null) {
				int currentStart = cnstrStr.indexOf("p");
				int currentEnd = cnstrStr.indexOf(" ");
				super.modifier = cnstrStr.substring(currentStart, currentEnd);
				currentStart = currentEnd + 1;
				currentEnd = cnstrStr.indexOf("(", currentStart);
				super.className = cnstrStr.substring(currentStart, currentEnd);
				currentStart = currentEnd + 1;
				currentEnd = cnstrStr.indexOf(")", currentStart);
				
				
				String argStr = cnstrStr.substring(currentStart, currentEnd);
				ArgumentList args = new ArgumentList();
				args.createArgList(argStr);
				super.argList = args;
//				System.out.println(cnstrStr.substring(currentStart, currentEnd)); // TODO - remove or log 	
			}
			return this;
		}
//		public ExistingConstructorBuilder addLine(String line) {
//			super.lines.addLine(line);			
//			return this;
//		}
		@Override
		protected ClassConstructor build() {
			return new ClassConstructor(this);
		}


	}

	
}
