/**
 * 
 */
package file.class_file.constructor;

import static file.annotation.SiteMapAnnotation.annotationTest;

import java.util.Scanner;

import file.class_file.constructor.ClassConstructor.ExistingConstructorBuilder;
import file.modifier.Modifier;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Map all the parts of an existing constructor
 * to the builder.
 */
public class ExistingConstructorMapper {
	private Scanner scanner;
	private ExistingConstructorBuilder builder;
	
	public ExistingConstructorMapper(Scanner scanner, ExistingConstructorBuilder builder) {
		this.scanner = scanner;
		this.builder = builder;
	}
	
	public ClassConstructor mapConstructor() {
		String line;		
		boolean end = false;
		
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				if(isAnnotation(line)) {
					builder.withAnnotation(line);			
				}else if (isDeclaration(line)) {
					builder.withConstructorDeclaration(line);
				}else if(isLine(line)) {
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
	private boolean isDeclaration(String line) {
		return (line != null && Modifier.startsWithValidModifier(line)) ? true : false;
	}
	private boolean isLine(String line) {
		return (line != null && line.length() > 0) ? true : false;
	}
	
}
