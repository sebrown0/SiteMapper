/**
 * 
 */
package file.helpers;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get lines from a file until EOF or blank line.
 */
public class LineMapper {
	public static Optional<String> findByFirstWord(Scanner scanner, Predicate<String> p) {
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
	public static <T> void mapLineToList(Scanner scanner, List<T> list, Predicate<String> p) {
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
}
