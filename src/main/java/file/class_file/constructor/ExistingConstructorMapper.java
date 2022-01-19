/**
 * 
 */
package file.class_file.constructor;

import static file.annotation.SiteMapAnnotation.annotationTest;
import static file.existing.ExistingFileScanner.variableTest;

import java.util.Optional;
import java.util.Scanner;

import file.class_file.constructor.ClassConstructor.ConstructorBuilder;
import file.class_file.constructor.ClassConstructor.ExistingConstructorBuilder;
import file.modifier.Modifier;
import file.variable.Variables;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ExistingConstructorMapper {
	private Scanner scanner;
	private ExistingConstructorBuilder builder;
	

	public ExistingConstructorMapper(Scanner scanner, ExistingConstructorBuilder builder) {
		this.scanner = scanner;
		this.builder = builder;
	}
	
	public ClassConstructor mapConstructor() {		
		
		Variables clazzVars = new Variables();		
		String line;		
		boolean end = false;
		
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				Optional<ConstructorBuilder> varBuilder = Optional.ofNullable(null);				
				if(isAnnotation(line)) {
					builder.withAnnotation(line);			
				}else if (isConstructorDeclration(line)) {
					builder.withConstructorStr(line);
				}else if(isConstructorLine(line)) {
					if(!line.equals("\t}")) {
						builder.addLine(line);
					}
				}else {
					end = true;
				}				
			}else {
				end = true;
			}
		}
		return builder.build();
	}
	private boolean isAnnotation(String line) {
		return (annotationTest.test(line)) ? true : false;
	}
	private boolean isConstructorDeclration(String line) {
		return (line != null && Modifier.startsWithValidModifier(line)) ? true : false;
	}
	private boolean isConstructorLine(String line) {
		return (line != null && line.length() > 0) ? true : false;
	}
	
	public String getAnnotation() {
		String a = null;
		return a;
	}
}
