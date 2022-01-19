/**
 * 
 */
package file.existing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import file.annotation.ExistingAnnotation;
import file.class_file.ClassBody;
import file.class_file.ClassBody.ExistingClassBody;
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
	private ExistingClassFileBuilder classBuilder = new ClassFile.ExistingClassFileBuilder();
		
	Pattern modifierPattern = Pattern.compile(".*public.*|.*protected.*|.*private.*");
	Pattern annotationPattern = Pattern.compile(".*@SiteMap.*");
	
	private Predicate<String> packageTest = s -> (s.startsWith("package"));
	private Predicate<String> commentTest = s -> (s.startsWith("*") || s.startsWith("/"));
	private Predicate<String> importTest = s -> (s.startsWith("import"));
	private Predicate<String> declarationTest = s -> (s.contains(" class "));
	private Predicate<String> annotationTest = s -> (annotationPattern.matcher(s).find());
	private Predicate<String> variableTest = s -> (modifierPattern.matcher(s).find());
	
	public ClassFile getClassFile() {
		return classBuilder.build();
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
			mapBody();
//			mapVariables();
			
			scanner.close();
		}		
	}
		
	private void mapPackage() {
		findByFirstWord(packageTest)
			.ifPresent(p -> classBuilder.setInPackage(new ExistingClassPackage(p)));		
	}
	public void mapImports() {
		ImportList imports = new ImportList();
		mapLineToList(imports.getImports(), importTest);
		classBuilder.setImports(imports);
	}
	public void mapComment() {
		ExistingComment comment = new ExistingComment();		
		mapLineToList(comment.getLines(), commentTest);
		classBuilder.setComment(comment);
	}
	private void mapDeclaration() {
		findByFirstWord(declarationTest).ifPresent(d -> {
			classBuilder.setDeclaration(
					new ClassDeclaration.ExistingDeclaration().setDeclarationString(d).build()
			);
		});		
	}
	private void mapBody() {
		ExistingClassBody bodyBuilder = new ClassBody.ExistingClassBody();
		bodyBuilder.setVars(mapVariables());		 
		//others...
		classBuilder.setClassBody(bodyBuilder.build());
	}
	private Variables mapVariables() {		
		Variables clazzVars = new Variables();		
		String line;		
		boolean end = false;
//		ClassVariable variable;
		
		while(!end && scanner.hasNext()) {
			line = scanner.nextLine();
			if(line.length() > 0) {
				Optional<Builder> builder = Optional.ofNullable(null);
				
				if(annotationTest.test(line)) {
					ExistingAnnotation anno = new ExistingAnnotation(line);
					line = scanner.nextLine();
					if(variableTest.test(line)) {
						builder = Optional.of(new ClassVariable.FromString(line).withAnnotation(anno));	
					}
					
				}else if(variableTest.test(line)) {
					builder = Optional.of(new ClassVariable.FromString(line));
				}else {
					end = true;
				}
				
				builder.ifPresent(b -> {
					clazzVars.addLine((ClassVariable) b.build());	
				});
				
				
//				classVariables.add(variable.toString()); //TODO
			}else {
				end = true;
			}
		}
		return clazzVars;		
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
