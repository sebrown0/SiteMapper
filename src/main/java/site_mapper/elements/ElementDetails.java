/**
 * 
 */
package site_mapper.elements;

import site_mapper.jaxb.pom.Element;
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
	Element setTestDataIn(ElementTestData data);
	Element setTestDataOut(ElementTestData data);
	ElementTestData getTestDataIn();
	ElementTestData getTestDataOut();
}
