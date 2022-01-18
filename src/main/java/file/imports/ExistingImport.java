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
	private String line;
	
	public ExistingImport(String line) {
		this.line = line;
	}

	@Override
	public String toString() {		
		return line + "\n";
	}	
}
