/**
 * 
 */
package file.method;

import static file.annotation.SiteMapAnnotation.annotationTest;

import java.util.Scanner;

import file.method.Method.ExistingMethodBuilder;
import file.modifier.Modifier;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ExistingMethodMapper {
	private Scanner scanner;
	private MethodList methods = new MethodList();
	private int openBraces;
	private int indent;
	
	public ExistingMethodMapper(Scanner scanner, int indent) {
		this.scanner = scanner;
		this.indent = indent;
	}
	
	public MethodList mapMethods() {
		while(scanner.hasNext()) {
			mapMethod();
		}
		return methods;
	}
		
	private void mapMethod() {
		ExistingMethodBuilder builder = new ExistingMethodBuilder(indent);
		String line;		
		boolean end = false, validMethod = false;
		
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {					
				line = removeUnwantedChars(line);				
				setBraces(line);				
				if(isAnnotation(line)) {
					builder.withAnnotation(line);			
				}else if (isDeclaration(line)) {
					validMethod = true;
					builder.withDeclarationStr(line);	
				}else if(isLine(line)) {
					addLine(line, builder);
				}else {
					end = true;
				}				
			}
		}
		
		if(validMethod) {
			methods.addMethod(builder.build());	
		}
	}
	
	private String  removeUnwantedChars(String line) {		
		return removeLeading(removeTrailing(line));
	}	
	private String removeLeading(String line) {
		if(line.startsWith("\t")) { //add more chars that we want to strip	
			line = line.substring(1);
		}
		return line;
	}
	private String removeTrailing(String line) {
		int numCharsToStrip = 0;
		for(int idx=line.length()-1; idx>0; idx--) {
			if(line.charAt(idx)==32) {//add more chars that we want to strip
				numCharsToStrip++;
			}else {
				break;
			}
		}
		return line.substring(0, line.length() - numCharsToStrip);		
	}
	
	private void addLine(String line, ExistingMethodBuilder builder) {
		builder.addLine(line);
	}
	private void setBraces(String line) {
		if(line.contains("{")){
			openBraces++;
		}else if(line.contains("}")){
			openBraces--;
		}		
	}
	private boolean isAnnotation(String line) {
		return (annotationTest.test(line)) ? true : false;
	}
	private boolean isDeclaration(String line) {
		return (line != null && Modifier.startsWithValidModifier(line)) ? true : false;
	}
	private boolean isLine(String line) {
		return (line != null && line.length() > 0 && isNotEndOfMethod(line)) ? true : false;
	}
	private boolean isNotEndOfMethod(String line) {
		return (line.contains("}") && openBraces == 0) ? false : true;
	}
	
}
