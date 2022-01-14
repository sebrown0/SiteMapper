/**
 * 
 */
package file.imports;

import site_mapper.creators.ImportType;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add a semicolon to the end of the import.
 * 
 */
public class NewImport extends Import<ImportType>{
	
	@Override
	public String toString() {
		String result = "";
		for (ImportType t : lines) {
			result += t.getPath() + "\n";
		}
//		return result + "\n";
		return result;
	}	
	
}
