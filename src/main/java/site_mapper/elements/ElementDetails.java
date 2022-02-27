/**
 * 
 */
package site_mapper.elements;

import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.TestDataIn;
import site_mapper.jaxb.pom.TestDataOut;

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
	Element setTestDataIn(TestDataIn data);
	Element setTestDataOut(TestDataOut data);
	TestDataIn getTestDataIn();
	TestDataOut getTestDataOut();
}
