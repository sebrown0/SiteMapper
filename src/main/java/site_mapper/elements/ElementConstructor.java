/**
 * 
 */
package site_mapper.elements;

import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ElementConstructor {
	String getModifier();
	String getClassName();
	String getSuperArgs();
	String getConstructorArgs();
	List<Object> getConstructorLines();	
}
