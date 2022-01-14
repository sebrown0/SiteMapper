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
 * Add a semicolon to the end of the import.
 * 
 */
public class NewImport extends Import{
	
	@Override
	public String toString() {
		String result = "";
		for (String s : lines) {
			result += s + ";\n";
		}
		return result + "\n";
	}	
	
}
