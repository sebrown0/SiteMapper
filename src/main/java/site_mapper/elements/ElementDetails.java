/**
 * 
 */
package site_mapper.elements;

import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.test_data.TestData;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataOut;

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
	Element setTestData(TestData data);
	TestDataIn getTestDataIn();
	TestDataOut getTestDataOut();
}
