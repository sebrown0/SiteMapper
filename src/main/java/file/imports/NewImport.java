/**
 * 
 */
package file.imports;

import site_mapper.creators.imports.FoundImports;
import site_mapper.creators.imports.ImportType;

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
	private FoundImports foundImports;
	
	public NewImport(ImportType line, FoundImports foundImports) {
		this.line = line;
		this.foundImports = foundImports;
	}

	public Import addLine(ImportType line) {
		this.line = line;
		return this;
	}
	
	@Override
	public String toString() {		
		return line.getPath(foundImports) + "\n";
	}
	@Override
	public String getImportString() {
		return line.getImportString();
	}	

}