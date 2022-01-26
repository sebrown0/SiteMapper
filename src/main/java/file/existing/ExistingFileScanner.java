/**
 * 
 */
package file.existing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

import file.class_file.ClassFile;
import file.class_file.ClassFile.ExistingClassFileBuilder;
import file.class_file.body.ClassBody;
import file.class_file.body.ClassBody.ExistingClassBody;
import file.imports.ExistingImport;
import file.imports.Import;
import file.modifier.Modifier;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initials
 * @since 1.0
 */
public class ExistingFileScanner {
	private FileReader reader;
	private Scanner scanner;	
	private ExistingClassFileBuilder classBuilder;
		
	public static final Predicate<String> PACKAGE_TEST = s -> (s.startsWith("package"));
	public static final Predicate<String> COMMENT_TEST = s -> (s.startsWith("*") || s.startsWith("/"));
	public static final Predicate<String> IMPORT_TEST = s -> (s.startsWith("import"));
	public static final Predicate<String> DECLARATION_TEST = s -> (s.contains(" class "));
	public static final Predicate<String> VARIABLE_TEST = s -> (Modifier.modifierPattern.matcher(s).find());
	
	public static final Function<String, Import> IMPORT_SUPPLIER = s -> new ExistingImport(s);
	public static final Function<String, String> STRING_SUPPLIER = s -> String.valueOf(s);
	
	public ClassFile getClassFile() {
		return classBuilder.build();
	}
		
	public boolean setScanner(String filePath) {
		if(filePath != null) {
			try {
				reader = new FileReader(filePath);
				scanner = new Scanner(reader);
				classBuilder = new ClassFile.ExistingClassFileBuilder(scanner);
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
			
			scanner.close();
		}		
	}
		
	private void mapPackage() {
		classBuilder.setInPackage();
	}
	public void mapImports() {		
		classBuilder.setImports();
	}
	public void mapComment() {		
		classBuilder.setComment();
	}
	private void mapDeclaration() {
		classBuilder.setDeclaration();
	}
	private void mapBody() {
		ExistingClassBody bodyBuilder = new ClassBody.ExistingClassBody(scanner);
		((ExistingClassBody) bodyBuilder
			.setVars()
			.setConstructor())
			.setMethods();		 
	 
		//others...
		classBuilder.setClassBody(bodyBuilder.build());
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
