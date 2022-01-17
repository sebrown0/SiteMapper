/**
 * 
 */
package file.variable;

import file.annotation.SiteMapAnnotation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Methods for setting a variable.
 */
public interface VariableSetter {
	VariableSetter setAnnotation(SiteMapAnnotation annotation);
	VariableSetter setType(String modifier);
	VariableSetter setName(String name);
	VariableSetter setValue(String value);
	void setFromString(String varStr);
}
