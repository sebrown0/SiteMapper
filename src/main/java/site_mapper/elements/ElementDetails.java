/**
 * 
 */
package site_mapper.elements;

import site_mapper.jaxb.pom.ElementTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ElementDetails {
	String getElementName();
	String getText();
	String getToolTipText(); 
	String getFafa();
	ElementTestData setTestData();
	ElementTestData getTestData();
}
