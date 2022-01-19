/**
 * 
 */
package site_mapper.creators;

import java.util.List;

import file.imports.Import;
import file.variable.Variable;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ComponentWriter {
	String getClassName();
	
	List<Variable> getClassVariables();
	List<Import> getImportNames();
	List<String> getSuperArgs();
	List<String> getConstructorArgs();
	List<String> getConstructorLines();
}
