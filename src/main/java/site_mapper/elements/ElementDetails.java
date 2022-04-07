/**
 * 
 */
package site_mapper.elements;

import java.util.List;

import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.test_data.TestData;
import site_mapper.jaxb.pom.test_data.TestDataItem;

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
	List<TestDataItem> getTestDataIn();
	List<TestDataItem> getTestDataOut();
}
