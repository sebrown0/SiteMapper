/**
 * 
 */
package file.imports;

import java.util.List;

import site_mapper.creators.ImportType;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the import from the ImportType.
 * 
 */
public class NewImport extends Import<ImportType>{
	
	public Import<ImportType> setLines(List<ImportType> lines) {
		if(lines != null) {
			this.lines.addAll(lines);
		}else {
			this.lines = lines;	
		}		
		return this;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (ImportType t : lines) {
			result += t.getPath() + "\n";
		}
		return result;
	}	
	
}
