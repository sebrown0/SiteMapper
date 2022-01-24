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
	private String superArgs;
	private Lines<Object> lines;
	private boolean hasControls = true;

	private ClassConstructor(ConstructorBuilder b) {
		this.cnstrAnnotation = b.cnstrAnnotation;
		this.modifier = b.modifier;
		this.className = b.className;
		this.argList = b.argList;
		this.superArgs = b.superArgs;
		this.lines = b.lines;
		this.hasControls = b.hasControls;
	}
	
	@Override
	public String toString() {		
		return String.format(
			"%s%s%s(%s){\n%s%s\t}",
			Formatter.getAnnotation(cnstrAnnotation),
			Formatter.getValueOf("\t", modifier),
			Formatter.getValueOf(" ", className), 
			Formatter.getValueOf(argList.toString()),
			getSuper(),
			insertCallToMyControls(),
			lines.toString()
		);
	}
		
	private String getSuper() {
		return (superArgs != null) ? "\t\tsuper(" + superArgs + ");\n" : "";
	}
	private String insertCallToMyControls() {
		return (hasControls) ? "\t\tbuildMyControls();\n" : "";
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
		private String superArgs;
		private Lines<Object> lines = new Lines<>().withIndent("\t\t");
		private boolean hasControls;
		
		public ConstructorBuilder addLine(Object obj) {
			String line = (String) obj;
			if(line.contains("super(")) {
				superArgs = ConstructorDeclarationMapper.mapSuperArgs(line);
			}else if (line.contains("buildMyControls")) {
				hasControls = true;
			}
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
		
		public NewConstructorBuilder callsBuildMyControls(boolean hasControls) {
			super.hasControls = hasControls;
			return this;
		}
		
		public NewConstructorBuilder withComponentInfo() {			
			super.modifier = cnstrInfo.getModifier();
			super.className = cnstrInfo.getClassName();
			super.argList =	new ArgumentList().createArgList(cnstrInfo.getConstructorArgs());
			super.superArgs = cnstrInfo.getSuperArgs();
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
