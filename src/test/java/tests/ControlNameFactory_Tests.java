package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import site_mapper.jaxb.pom.Element;

class ControlNameFactory_Tests {

	@Test
	void test_invalidButton() {
		Element btn = new Element();
		btn.setType("xbutton").setName("company");
		
		assertEquals("Company", btn.getNameWithAcronym());
	}
	
	@Test
	void test_validButton() {
		Element btn = new Element();
		btn.setType("button").setName("company");
		
		assertEquals("btnCompany", btn.getNameWithAcronym());
	}

}
