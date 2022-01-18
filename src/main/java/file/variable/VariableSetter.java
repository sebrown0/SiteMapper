/**
 * 
 */
package file.variable;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Methods for setting a variable.
 */
public interface VariableSetter {
//	VariableSetter setAnnotation(SiteMapAnnotation annotation);
	VariableSetter setModifier(String modifier);
	VariableSetter setName(String name);
	VariableSetter setValue(String value);
}
