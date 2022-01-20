/**
 * 
 */
package file.helpers;

import java.util.List;

import file.annotation.SiteMapAnnotation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Various ways formatting a string for a site mapped class.
 */
public class Formatter {

	public static String getAnnotation(String tabs, SiteMapAnnotation annotation) {
		if(annotation != null) {
			String ano = annotation.toString();
			return (ano != null) ? tabs + ano + "\n" : "";	
		}else {
			return "";
		}		
	}
	public static String getAnnotation(SiteMapAnnotation annotation) {
		if(annotation != null) {
			String ano = annotation.toString();
			return (ano != null) ? ano + "\n" : "";	
		}else {
			return "";
		}		
	}
	
	public static String getAnnotation(SiteMapAnnotation annotation, String withTabs) {
		if(annotation != null) {
			String ano = annotation.toString();
			return (ano != null) ? withTabs + ano + "\n" : "";	
		}else {
			return "";
		}		
	}
	
	public static String getVariableValue(String value, String type) {
		String ret = "";
		if(value != null) {
			if(type.contains("String")) {
				if(value.startsWith("\"")){
					ret = " = " + value;	
				}else {
					ret = " = \"" + value;					
				}				
				if(!(value.endsWith("\""))){
					ret += "\"";
				}
			}else {
				ret = " = " + value;
			}
		}
		return ret;
	}
	
	public static String getVariableValue(String value) {		
		return (value != null) ? " = " + value : "";
	}
	
	public static <T> String getValueOf(T value) {
		return (value != null) ? value.toString() : "";
	}
	public static String getValueOf(String value) {
		return (value != null) ? value : "";
	}
	public static String getValueOf(String prepend, String value) {
		return (value != null) ? prepend + value : "";
	}
	
	public static String getValuePair(String value, String valueTag) {
		return (value != null) ? valueTag + "=\"" + value + "\"" : "";
	}
	
	public static <T> String getAsCommaSeparatedList(List<T> listOfVals) {
		String ret = "";
		if(listOfVals != null) {			
			for (T t : listOfVals) {
				ret += String.format("%s, ", t.toString());				
			}
			if(listOfVals.size()>=1) { ret = ret.substring(0, ret.length()-2); }
		}
		return ret;
	}
	
	public static String getEndOfLine(String line) {		
		return (line != null && line.endsWith(";")) ? line : line + ";";
	}
}
