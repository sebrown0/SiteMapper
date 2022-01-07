/**
 * 
 */
package site_mapper.creators;

import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ComponentWriter {
	String getClassName();
	List<ImportType> getImportNames();
	List<String> getSuperArgs();
	List<String> getConstructorArgs();
	List<String> getConstructorLines();
}
