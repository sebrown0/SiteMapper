/**
 * 
 */
package file.class_file;

import java.util.ArrayList;
import java.util.List;

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
	private String modifier;
	private String name;
	private List<String> extended;
	private List<String> implemented;
	
	public ClassDeclaration(String modifier, String name) {
		this.modifier = modifier;
		this.name = name;
	}

	public void setExtended(List<String> extended) {
		if(this.extended == null) { 
			this.extended = extended; 
		}else {
			this.extended.addAll(extended);	
		}		
	}

	public ClassDeclaration addExtended(String extended) {
		if(this.extended == null) { this.extended = new ArrayList<>(); }
		this.extended.add(extended);
		return this;
	}
	
	public void setImplemented(List<String> implemented) {
		if(this.implemented == null) { 
			this.implemented = implemented; 
		}else {
			this.implemented.addAll(implemented);	
		}		
	}
	

	public ClassDeclaration addImplemented(String implemented) {
		if(this.implemented == null) { this.implemented = new ArrayList<>(); }
		this.implemented.add(implemented);
		return this;
	}
	
	@Override
	public String toString() {		
		return modifier + " class " + name + getExtends() + getImplements() + " {\n";
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
}
