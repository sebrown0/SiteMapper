package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import site_mapper.creators.control_data.ElementData;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.Locator;

class ElementData_Tests {
	private static Element e = 
			new Element()
				.setName("save")
				.setType("button")
				.setBy("css")
				.setLocator("button[name='SAVE']");
	
	@Test
	void locator() {
		Locator loc = e.getLocator();		
		assertEquals("By.cssSelector(\"button[name='SAVE']\")", loc.toString());
	}
	
	@Test
	void test() {
		final String res = 
				"\t\tControlGetter save =\n" +
				"\t\t\tnew ControlGetterButton(\"Save\", coreData, By.cssSelector(\"button[name='SAVE']\"));";
		
		ElementData elData = e;		
		assertEquals(res, elData.getElementString());
	}

}
