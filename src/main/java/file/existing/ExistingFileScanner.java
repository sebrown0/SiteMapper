/**
 * 
 */
package file.existing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import file.annotation.ExistingAnnotation;
import file.class_file.ClassBody;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_file.ClassFile.ExistingClassFileBuilder;
import file.class_package.ExistingClassPackage;
import file.comment.ExistingComment;
import file.imports.ImportList;
import file.variable.ClassVariable;
import file.variable.Variable.Builder;
import file.variable.Variables;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initials
 * @since 1.0
 */
public class ExistingFileScanner {
	private FileReader reader;
	private Scanner scanner;	
	private ExistingClassFileBuilder builder = new ClassFile.ExistingClassFileBuilder();
	
	private ClassBody classBody = new ClassBody();
	
	Pattern modifierPattern = Pattern.compile(".*public.*|.*protected.*|.*private.*");
	Pattern annotationPattern = Pattern.compile(".*@SiteMap.*");
	
	private Predicate<String> packageTest = s -> (s.startsWith("package"));
	private Predicate<String> commentTest = s -> (s.startsWith("*") || s.startsWith("/"));
	private Predicate<String> importTest = s -> (s.startsWith("import"));
	private Predicate<String> declarationTest = s -> (s.contains(" class "));
	private Predicate<String> annotationTest = s -> (annotationPattern.matcher(s).find());
	private Predicate<String> variableTest = s -> (modifierPattern.matcher(s).find());
	
	public ClassFile getClassFile() {
		return builder.build();
	}
		
	public boolean setScanner(String filePath) {
		if(filePath != null) {
			try {
				reader = new FileReader(filePath);
				scanner = new Scanner(reader);
				return true;
			} catch (FileNotFoundException e) {
				//TODO - log error.
				return false;
			}			
		}else {
			//TODO - log error.
			return false;
		}
	}
	
	public void mapFile() {
		if(scanner != null) {
			mapPackage();
			mapImports();
			mapComment();
			mapDeclaration();
			mapVariables();
			
			scanner.close();
		}		
	}
		
	private void mapPackage() {
		findByFirstWord(packageTest)
			.ifPresent(p -> builder.setInPackage(new ExistingClassPackage(p)));		
	}
	public void mapImports() {
		ImportList imports = new ImportList();
		mapLineToList(imports.getImports(), importTest);
		builder.setImports(imports);
	}
	public void mapComment() {
		ExistingComment comment = new ExistingComment();		
		mapLineToList(comment.getLines(), commentTest);
		builder.setComment(comment);
	}
	private void mapDeclaration() {
		findByFirstWord(declarationTest).ifPresent(d -> {
			builder.setDeclaration(
					new ClassDeclaration.ExistingDeclaration().setDeclarationString(d).build()
			);
		});		
	}
	public void mapVariables() {
//		List<String> classVariables;
//		classVariables = new ArrayList<>();
		
		Variables clazzVars = new Variables();
		
		String line;		
		boolean end = false;
		ClassVariable variable;
		
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				Builder builder = new ClassVariable.FromString(line);
				
				if(annotationTest.test(line)) {
					ExistingAnnotation anno = new ExistingAnnotation(line);
					builder.withAnnotation(anno);					
				}else if(variableTest.test(line)) {
					// TODO
				}else {
					end = true;
				}
				variable = (ClassVariable) builder.build();
				clazzVars.addLine(variable);
				
//				classVariables.add(variable.toString()); //TODO
			}else {
				end = true;
			}
		}		
	}

	private Optional<String> findByFirstWord(Predicate<String> p) {
		Optional<String> res = Optional.ofNullable(null);
		String line;
		
		while(scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				if(p.test(line)) {
					res = Optional.of(line); 	
					break;
				}
			}else {
				line = scanner.nextLine();
			}
		}		
		return res;
	}
	@SuppressWarnings("unchecked")
	private <T> void mapLineToList(List<T> list, Predicate<String> p) {
		String line;		
		boolean end = false;
		boolean removedFirstBlankLine = false;
		
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				removedFirstBlankLine = true;
				if(p.test(line)) {
					list.add((T) line); 	
				}
			}else if (removedFirstBlankLine == false) {
				removedFirstBlankLine = true;
			}else {
				end = true;
			}
		}			
	}
		

	public void declaration(Scanner scanner) {
		if(scanner.hasNext()) {
			String line = scanner.nextLine();
			if(line.length() > 0 && (line.startsWith("\t p"))) {
				System.out.println(line); // TODO - remove or log 	
			}else {
				//TODO - error it should be the declaration
			}
		}
	}
	
	public void classVars(Scanner scanner) {
		boolean end = false;
		String line;
		scanner.useDelimiter(";");
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				System.out.println(line); // TODO - remove or log 	
			}else {
				end = true;
			}
		}
	}
	
	public void constructor(Scanner scanner) {
		getMethod(scanner);
	}
	
	public void methods(Scanner scanner) {
		while(scanner.hasNext()) {
			getMethod(scanner);
		}
	}
	
	private void getMethod(Scanner scanner) {
		boolean end = false;
		String line;
		scanner.useDelimiter("\t}");
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				System.out.println(line); // TODO - remove or log 	
			}else {
				end = true;
			}
		}
	}
}
