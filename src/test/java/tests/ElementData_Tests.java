package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import site_mapper.creators.control_data.ElementData;
import site_mapper.jaxb.pom.Element;

class ElementData_Tests {

//	ControlGetter save = 
//			new ControlGetterButton("Save", coreData, By.cssSelector("button[name='SAVE']"));
	

	 /* 
	 * SHOULF GET THE ELEMENT STRING FROM ELEMENT!!!!!!!!!!!!!!!!!!
	 * LOCATOR SHOULD RETURN LOCATOR STRING
	 * 
	 */
	 
	@Test
	void test() {
		final String res = 
				"\t\tControlGetter save = \n" +
				"\t\t\tnew ControlGetterButton(\"Save\", coreData, By.cssSelector(\"button[name='SAVE']\"));";
		
		Element e = 
			new Element()
				.setName("save")
				.setType("button")
				.setBy("css")
				.setLocator("button[name='SAVE']");
		
		ElementData elData = new ElementData(e);
		
		assertEquals(res, elData.getDeclaration());
	}

}
