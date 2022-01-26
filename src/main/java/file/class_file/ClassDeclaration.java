/**
 * 
 */
package file.class_file;

import java.util.ArrayList;
import java.util.List;

import file.helpers.Formatter;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.menu_items.TypeAttributes;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for a class declaration in a site mapped class.
 */
public class ClassDeclaration {	
	private final String modifier;
	private final String name;
	private final String extend;
	private final List<String> implemented;
	
	public ClassDeclaration(Builder b) {
		this.modifier = b.getModifier();
		this.name = b.getName();
		this.extend = b.getExtended();
		this.implemented = b.getImplemented();
	}
	
	@Override
	public String toString() {		
		return modifier + " class " + name + getExtends() + getImplements() + " {";
	}
	
	private String getExtends() {		
		return (extend != null && extend.length() > 0) ? " extends " + extend : "";
	}
	private String getImplements() {
		String result = getCommaSepList(implemented);
		return (result.length() > 0) ? " implements " + result : "";
	}
	
	private <T> String getCommaSepList(List<T> list) {
		if(list != null) {
			return Formatter.getAsCommaSeparatedList(list);
		}else {
			return "";
		}
	}
	
	public static abstract class Builder {
		protected String modifier;
		protected String name;
		protected String extend;
		protected List<String> implemented;
		
		public abstract ClassDeclaration build();

		public String getModifier() {
			return modifier;
		}
		public String getName() {
			return name;
		}
		public String getExtended() {
			return extend;
		}
		public List<String> getImplemented() {
			return implemented;
		}		
	}
	
	public static class ExistingDeclaration extends Builder {
		private String declarationStr;
		private int currentEnd;
		
		public ExistingDeclaration setDeclarationString(String declarationStr) {
			this.declarationStr = declarationStr;
			
			setModifier();
			setName();
			setExtends();
			setImplements();
			
			return this;
		}

		private void setModifier() {
			currentEnd = declarationStr.indexOf(" ");
			modifier = declarationStr.substring(0, currentEnd);
		}
		private void setName() {
			var start = declarationStr
					.indexOf(" ", declarationStr.indexOf("class", currentEnd) + 1) + 1;
			currentEnd = declarationStr.indexOf(" ", start);
			name = declarationStr.substring(start, currentEnd);
		}
		private void setExtends() {
			var start = declarationStr.indexOf("extends", currentEnd) + 8;
			if(start >= 8) {
				currentEnd = declarationStr.indexOf(" ", start);
				extend = declarationStr.substring(start, currentEnd);
			}
		}
		private void setImplements() {
			var start = declarationStr.indexOf("implements", currentEnd) + 10;
			if(start >= 10) {
				implemented = new ArrayList<>();
				currentEnd = declarationStr.indexOf("{", start);
				String[] parts = declarationStr.substring(start, currentEnd).split(",");
				for(int idx=0; idx < parts.length; idx++) {
					implemented.add(parts[idx].trim());
				}
			}
		}
		
		@Override
		public ClassDeclaration build() {
			return new ClassDeclaration(this);
		}		
	}
	
	public static class NewDeclaration extends Builder {
		private ElementClass clazz;
		private TypeAttributes attributes;
		
		public NewDeclaration setDeclarationClazz(ElementClass clazz) {
			this.clazz = clazz;
			this.attributes = clazz.getMenuItemType().getAttributes();
			
			setModifier();
			setName();
			setExtends();
			setImplements();
			
			return this;
		}
		
		@Override
		public ClassDeclaration build() {
			return new ClassDeclaration(this);
		}		
		
		private void setModifier() {
			modifier = "public"; 			/** Default to public. **/
		}
		private void setName() {
			name = clazz.getClassName().trim();
		}
		private void setExtends() {			
			extend = attributes.getExtends();
		}
		private void setImplements() {
			
		}
	}

	public String getName() {
		return name;
	}
	
}
