/**
 * 
 */
package file.variable;

import static file.existing.ExistingFileScanner.VARIABLE_TEST;

import java.util.Optional;
import java.util.Scanner;

import file.annotation.ExistingAnnotation;
import file.annotation.SiteMapAnnotation;
import file.variable.Variable.VariableBuilder;


/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the class variables along with any
 * annotation from an existing class file.
 */
public class ExistingClassVariableMapper {	
	private Scanner scanner;	
	
	public ExistingClassVariableMapper(Scanner scanner) {
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
				if(SiteMapAnnotation.isAnnotation(line)) {
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
	
	private boolean isVariable(String line) {
		return (VARIABLE_TEST.test(line)) ? true : false;
	}
	
	private Optional<VariableBuilder> mapAnnotationAndVar(Optional<VariableBuilder> varBuilder, String line) {
		ExistingAnnotation anno = new ExistingAnnotation(line, 1); //If this is made into generic class for vars the indent will have to be assessed.
		line = scanner.nextLine();
		if(isVariable(line)) {
			varBuilder = Optional.of(new ClassVariable.ClassVarFromString(line).withAnnotation(anno));	
		}
		return varBuilder;				
	}
	
	private Optional<VariableBuilder> mapVar(Optional<VariableBuilder> varBuilder, String line) {
		if(isVariable(line)) {
			varBuilder = Optional.of(new ClassVariable.ClassVarFromString(line));	
		}
		return varBuilder;				
	}

	private void addVarToClass(Optional<VariableBuilder> varBuilder, Variables clazzVars) {
		varBuilder.ifPresent(b -> {
			clazzVars.addLine((ClassVariable) b.build());	
		});
	}
}
