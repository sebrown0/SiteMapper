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
public interface ComponentInfo {
	String getClassName();
	String getModifier();
	String getConstructorArgs();
	String getSuperArgs();
	
	List<Variable> getClassVariables();
	List<Import> getImportNames();	
	List<Object> getConstructorLines();
}
