/**
 * 
 */
package file.imports;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Does not add a semicolon to the end of 
 * the import as it's existing and should 
 * already have one.
 */
public class ExistingImport extends Import{
	
	@Override
	public String toString() {
		String result = "";
		for (String s : lines) {
			result += s + "\n";
		}
		return result + "\n";
	}	
}
