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
public interface ImportType {
	String getImportString();
	String getPath();
	String getPath(FoundImports foundImports);	
}
