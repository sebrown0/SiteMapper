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

import file.class_file.ClassBody;
import file.class_file.ClassBody.ExistingClassBody;
import file.class_file.ClassDeclaration;
import file.class_file.ClassFile;
import file.class_file.ClassFile.ExistingClassFileBuilder;
import file.class_package.ExistingClassPackage;
import file.comment.ExistingComment;
import file.imports.ImportList;
import file.variable.ExistingVariableMapper;
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
		
	public static final Pattern modifierPattern = Pattern.compile(".*public.*|.*protected.*|.*private.*");
	public static final Pattern annotationPattern = Pattern.compile(".*@SiteMap.*");
	
	public static final Predicate<String> packageTest = s -> (s.startsWith("package"));
	public static final  Predicate<String> commentTest = s -> (s.startsWith("*") || s.startsWith("/"));
	public static final Predicate<String> importTest = s -> (s.startsWith("import"));
	public static final Predicate<String> declarationTest = s -> (s.contains(" class "));
	public static final Predicate<String> annotationTest = s -> (annotationPattern.matcher(s).find());
	public static final Predicate<String> variableTest = s -> (modifierPattern.matcher(s).find());
	
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
		ExistingVariableMapper mapper = new ExistingVariableMapper(scanner);
		return mapper.mapVariables();
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
