/**
 * 
 */
package file.variable;

import static file.existing.ExistingFileScanner.annotationTest;
import static file.existing.ExistingFileScanner.variableTest;

import java.util.Optional;
import java.util.Scanner;

import file.annotation.ExistingAnnotation;
import file.variable.Variable.VariableBuilder;


/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ExistingVariableMapper {	
	private Scanner scanner;	
	
	public ExistingVariableMapper(Scanner scanner) {
		this.scanner = scanner;
	}

	public Variables mapVariables() {		
		Variables clazzVars = new Variables();		
		String line;		
		boolean end = false;
		
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				Optional<VariableBuilder> varBuilder = Optional.ofNullable(null);				
				if(isAnnotation(line)) {
					varBuilder = mapAnnotationAndVar(varBuilder, line);			
				}else if(isVariable(line)) {
					varBuilder = mapVar(varBuilder, line);
				}else {
					end = true;
				}
				addVarToClass(varBuilder, clazzVars);				
			}else {
				end = true;
			}
		}
		return clazzVars;		
	}
		
	private boolean isAnnotation(String line) {
		return (annotationTest.test(line)) ? true : false;
	}
	
	private boolean isVariable(String line) {
		return (variableTest.test(line)) ? true : false;
	}
	
	private Optional<VariableBuilder> mapAnnotationAndVar(Optional<VariableBuilder> varBuilder, String line) {
		ExistingAnnotation anno = new ExistingAnnotation(line);
		line = scanner.nextLine();
		if(isVariable(line)) {
			varBuilder = Optional.of(new ClassVariable.FromString(line).withAnnotation(anno));	
		}
		return varBuilder;				
	}
	
	private Optional<VariableBuilder> mapVar(Optional<VariableBuilder> varBuilder, String line) {
		if(isVariable(line)) {
			varBuilder = Optional.of(new ClassVariable.FromString(line));	
		}
		return varBuilder;				
	}

	private void addVarToClass(Optional<VariableBuilder> varBuilder, Variables clazzVars) {
		varBuilder.ifPresent(b -> {
			clazzVars.addLine((ClassVariable) b.build());	
		});
	}
}
