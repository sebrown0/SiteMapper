/**
 * 
 */
package file.class_file.constructor;

import file.annotation.ExistingAnnotation;
import file.annotation.NewAnnotation;
import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
import file.helpers.Lines;
import file.variable.ArgumentList;
import site_mapper.elements.ElementConstructor;
import site_mapper.jaxb.pom.SiteMapInfo;

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
	private Lines<Object> lines;

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
			Formatter.getAnnotation(cnstrAnnotation),
			Formatter.getValueOf("\t", modifier),
			Formatter.getValueOf(" ", className), 
			Formatter.getValueOf(argList.toString()),
			lines.toString()
		);
	}
		
	/**
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
		private Lines<Object> lines = new Lines<>().withIndent("\t\t");
		
		public ConstructorBuilder addLine(Object obj) {
			this.lines.addLine(obj);
			return this;
		}
		
		public ClassConstructor build() {
			return new ClassConstructor(this);
		}
	}
	
	/**
	 * @author SteveBrown
	 * @version 1.0
	 * 	Initial
	 * @since 1.0
	 */
	public static class NewConstructorBuilder extends ConstructorBuilder {
		private SiteMapInfo siteInfo;
		private ElementConstructor cnstrInfo;
		
		public NewConstructorBuilder(SiteMapInfo siteInfo, ElementConstructor cnstrInfo) {
			this.siteInfo = siteInfo;
			this.cnstrInfo = cnstrInfo;
		}

		public NewConstructorBuilder withAnnotation() {
			if(siteInfo != null) {
				super.cnstrAnnotation = new NewAnnotation(siteInfo, 1);
			}
			return this;
		}
		
		public NewConstructorBuilder withComponentInfo() {			
			super.modifier = cnstrInfo.getModifier();
			super.className = cnstrInfo.getClassName();
			super.argList =	new ArgumentList().createArgList(cnstrInfo.getConstructorArgs());
			super.lines = new Lines<>().setLines(cnstrInfo.getConstructorLines());
			return this;
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
		
		public ExistingConstructorBuilder withAnnotation(String annoStr) {
			if(annoStr != null) {
				super.cnstrAnnotation = new ExistingAnnotation(annoStr, 1);
			}
			return this;
		}

		public ExistingConstructorBuilder withConstructorDeclaration(String cnstrStr) {
			ConstructorDeclarationMapper mapper = new ConstructorDeclarationMapper();
			mapper.mapDeclaration(cnstrStr);
			super.modifier = mapper.getModifier().toString();
			super.className = mapper.getName();
			super.argList = mapper.getArgs();

			return this;
		}		
	}
		
}
