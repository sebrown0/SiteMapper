/**
 * 
 */
package site_mapper.elements;

import java.util.Arrays;
import java.util.List;

import site_mapper.jaxb.pom.ElementTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ElementDataList implements TestData {
	private ElementTestData elementTestData;

	@Override
	public TestData setData(ElementTestData elementTestData) {
		this.elementTestData = elementTestData;
		return this;
	}
	
	public List<String> getData() {
		List<String> res = null;
		String listAsStr = elementTestData.getList();
		if(listAsStr != null) {
			res = Arrays.asList(listAsStr.split(","));
		}
		return res;
	}	

}
