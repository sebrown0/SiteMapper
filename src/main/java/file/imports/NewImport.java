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
 * Get the import from the ImportType.
 * 
 */
public class NewImport extends Import {
	private ImportType line;
	
	public NewImport(ImportType line) {
		this.line = line;
	}

	public Import addLine(ImportType line) {
		this.line = line;
		return this;
	}
	
	@Override
	public String toString() {		
		return line.getPath() + "\n";
	}

}