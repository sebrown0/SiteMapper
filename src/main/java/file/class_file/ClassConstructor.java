/**
 * 
 */
package file.class_file;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
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
	private Variables arguments;
	
	private ClassConstructor(ConstructorBuilder b) {
		this.cnstrAnnotation = b.cnstrAnnotation;
		this.modifier = b.modifier;
		this.className = b.className;
		this.arguments = b.arguments;
	}
	
	public abstract static class ConstructorBuilder {
		private SiteMapAnnotation cnstrAnnotation;
		private String modifier;
		private String className;
		private Variables arguments;
		
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
		public void setArguments(Variables arguments) {
			this.arguments = arguments;
		}
	}
	
	public static class ExistingConstructorBuilder extends ConstructorBuilder {

		@Override
		public ConstructorBuilder withAnnotation(String annoStr) {
			if(annoStr != null) {
				super.cnstrAnnotation = new ExistingAnnotation(annoStr);
			}
			return this;
		}
		public ConstructorBuilder withConstructor(String cnstrStr) {
			if(cnstrStr != null) {
				String m = cnstrStr.substring(0, cnstrStr.indexOf(" ")-1);
				super.modifier = m;
			}
			return this;
		}
		
		@Override
		protected ClassConstructor build() {
			return new ClassConstructor(this);
		}


	}

	@Override
	public String toString() {		
		return String.format("%s\t%s %s(){\n\t}", getAnnotation(), modifier, className);
	}
	
	private String getAnnotation() {
		return (cnstrAnnotation != null) ? "\t" + cnstrAnnotation + "\n" : "";
	}
}
