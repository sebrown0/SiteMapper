/**
 * 
 */
package file.class_file;

import java.util.ArrayList;
import java.util.List;

import file.class_file.ClassFile.Builder;
import file.helpers.Formatter;

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
	private List<String> extended;
	private List<String> implemented;
	
	private String theDeclaration;
	
	public ClassDeclaration(String modifier, String name) {
		this.modifier = modifier;
		this.name = name;
	}

	public ClassDeclaration addExtended(String extended) {
		if(this.extended == null) { this.extended = new ArrayList<>(); }
		this.extended.add(extended);
		return this;
	}		

	public ClassDeclaration addImplemented(String implemented) {
		if(this.implemented == null) { this.implemented = new ArrayList<>(); }
		this.implemented.add(implemented);
		return this;
	}
	
	@Override
	public String toString() {		
//		return modifier + " class " + name + getExtends() + getImplements() + " {\n";
		return theDeclaration;
	}
	
	private String getExtends() {
		String result = getCommaSepList(extended);
		return (result.length() > 0) ? " extends " + result : "";
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
		protected List<String> extended;
		protected List<String> implemented;
		
		protected String theDeclaration;
		
		public abstract ClassDeclaration build();			
	}
	
	public static class ExistingDeclaration extends Builder {

		@Override
		public ClassDeclaration build() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
