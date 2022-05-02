package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import site_mapper.creators.control_type.ControlTypeButton;
import site_mapper.creators.control_type.ControlTypeComboWriteAndSelect;
import site_mapper.creators.control_type.ControlTypeFactory;
import site_mapper.creators.control_type.ControlTypeLabel;
import site_mapper.creators.control_type.ControlTypeTextOut;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.Locator;

class ControlTypeFactory_Tests {

	@Test
	void test_creation() {
		Element e = new Element().setType("Button");
		assertTrue(ControlTypeFactory.getControlType(e) instanceof ControlTypeButton);		
	}

	@Test
	void test_nameWithAcronym() {
		Element el = new Element();
		el.setType("button").setName("company");
		
		ControlTypeButton btn = (ControlTypeButton) ControlTypeFactory.getControlType(el);
		assertEquals("btnCompany", btn.getNameWithAcronym());		
	}
	
	@Test
	void test_controlDataStr_forBtn() {
		Element el = new Element();
		el.
			setType("button").
			setName("company").
			addLocator(
					new Locator()
						.setBy("css")
						.setLocator("class=[name='Comp']"));
		
		ControlTypeButton btn = (ControlTypeButton) ControlTypeFactory.getControlType(el);
		assertEquals(
				"\t\tControlGetter btnCompany =" +
				"\n\t\t\tnew ControlGetterButton(\"btnCompany\", coreData, By.cssSelector(\"class=[name='Comp']\"), this);", 
				btn.getControlDataString());		
	}

	@Test
	void test_controlDataStr_forLabel() {
		Element el = new Element();
		el.
			setType("Label").
			setName("company").
			addLocator(
					new Locator()
						.setBy("css")
						.setLocator("class=[name='Comp']"));
		
		ControlTypeLabel lbl = (ControlTypeLabel) ControlTypeFactory.getControlType(el);
		assertEquals(
				"\t\tControlGetter lblCompany =" +
				"\n\t\t\tnew ControlGetterLabel(\"lblCompany\", coreData, By.cssSelector(\"class=[name='Comp']\"), this);", 
				lbl.getControlDataString());		
	}
	
	@Test
	void test_controlDataStr_forTextOut() {
		Element el = new Element();
		el.
			setType("TextOut").
			setName("FormID").
			addLocator(
					new Locator()
						.setBy("id")
						.setLocator("FORM_ID"));
		
		ControlTypeTextOut txt = (ControlTypeTextOut) ControlTypeFactory.getControlType(el);
		assertEquals(
				"\t\tControlGetter txoFormID =" +
				"\n\t\t\tnew ControlGetterTextOut(\"txoFormID\", coreData, By.id(\"FORM_ID\"), this);", 
				txt.getControlDataString());		
	}
	
	@Test
	void test_controlDataStr_forComboWriteAndSelect() {
		Element el = new Element();
		el.
			setType("ComboWriteAndSelect").
			setName("company").
			addLocator(
					new Locator()
						.setBy("css")
						.setLocator("span[id='select2-COMP_SELx-container']"));
		
		ControlTypeComboWriteAndSelect cmb = (ControlTypeComboWriteAndSelect) ControlTypeFactory.getControlType(el);
		assertEquals(
				"\t\tControlGetter cwsCompany =" +
				"\n\t\t\tnew ControlGetterComboWriteAndSelect(" +
				"\n\t\t\t\t\"cwsCompany\", coreData" +
				"\n\t\t\t\t, By.cssSelector(\"span[id='select2-COMP_SELx-container']\")," +
				"\n\t\t\t\tBy.className(\"select2-results\")," +
				"\n\t\t\t\tnew TextWriterComboMulti(coreData, getContainer()));", 
				cmb.getControlDataString());		
	}
}
