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
public class ElementDataText implements TestData {	
	private ElementTestData elementTestData;
	
	@Override
	public TestData setData(ElementTestData elementTestData) {
		this.elementTestData = elementTestData;
		return this;
	}
	
	public String getText() {
		return elementTestData.getText();
	}

}
