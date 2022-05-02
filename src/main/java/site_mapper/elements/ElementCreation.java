/**
 * 
 */
package site_mapper.elements;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Items required when an element is created.
 */
public interface ElementCreation extends ElementDetails {	
	String getElementType();	
	String getLocators();
}
