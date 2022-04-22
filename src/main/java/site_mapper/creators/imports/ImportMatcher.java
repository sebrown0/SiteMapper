/**
 * 
 */
package site_mapper.creators.imports;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ImportMatcher {
	private FoundImports foundImports;

	public ImportMatcher(FoundImports foundImports) {
		this.foundImports = foundImports;
	}
	
	public void matchImports(ImportResolver importResolver) {
		importResolver.resolveImports(foundImports);
	}

	public FoundImports getFoundImports() {
		return foundImports;
	}
	
}
