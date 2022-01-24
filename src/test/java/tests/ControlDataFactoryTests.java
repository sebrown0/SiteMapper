/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import exceptions.InvalidArgumentException;
import file.annotation.ExistingAnnotation;
import site_mapper.creators.ControlDataFunctionFactory;
import site_mapper.creators.ControlDataValues;
import site_mapper.elements.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Test that the string produced by the ControlDataStringFactory
 * is correct.
 * 
 * The values passed to the factory are not necessarily representative
 * of 'real' values.
 * 
 */
class ControlDataFactoryTests {

	@Test
	void incorrectControlName() {		
		Element e = new Element();
		e.setName(null).setType(null).setLocator(null).setBy("css");
		
		Exception ex = assertThrows(
			InvalidArgumentException.class, 
			() -> ControlDataFunctionFactory.getControlData(new ControlDataValues(e)));
		assertEquals("[null] is not a valid control type name.", ex.getMessage());
	}
	@Test
	void incorrectByValue() {
		Element e = new Element();
		e.setName("button").setType(null).setLocator(null).setBy(null);
		
		Exception ex = assertThrows(
			InvalidArgumentException.class, 
			() -> ControlDataFunctionFactory.getControlData(new ControlDataValues(e)));
		assertEquals("[null] is not a valid By type name.", ex.getMessage());
	}
	@Test
	void check_Button_with_CSS() throws InvalidArgumentException {
		Element e = new Element();
		e.setName("search").setType("button").setLocator("\"button[name='QBF1']\"").setBy("css");
		
		String s = ControlDataFunctionFactory.getControlData(new ControlDataValues(e)).get();
		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"\"button[name='QBF1']\"\")))", s);
	}
	@Test
	void check_Button_with_XPATH() throws InvalidArgumentException {
		Element e = new Element();
		e.setName("search").setType("button").setLocator("\"button[name='QBF1']\"").setBy("xpath");
		
		String s = ControlDataFunctionFactory.getControlData(new ControlDataValues(e)).get();
		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.xpath(\"\"button[name='QBF1']\"\")))", s);
	}
	@Test
	void check_Button_with_ID() throws InvalidArgumentException {
		Element e = new Element();
		e.setName("search").setType("button").setLocator("\"button[name='QBF1']\"").setBy("id");
		
		String s = ControlDataFunctionFactory.getControlData(new ControlDataValues(e)).get(); 	
		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.id(\"\"button[name='QBF1']\"\")))", s);		 	
	}
	@Test
	void buildControlsFunction() throws InvalidArgumentException {
		String expected = 
				"\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")\n" +
				"\tprivate void buildMyControls() {\n" +
				"\t\tvar myControls = \r\n" +
				"\t\t\tList.of(" +
				"\n\t\t\t\tnew ControlData(\"search\", new ControlGetterButton(coreData, By.id(\"\"button[name='QBF1']\"\")))," +
				"\n\t\t\t\tnew ControlData(\"save\", new ControlGetterButton(coreData, By.cssSelector(\"\"button[name='QBF2']\"\")))" +
				"\n\t\t\t);\n\t\tsuper.buildPanelControls(myControls);\n\t}";
		
		Element e1 = new Element();
		e1.setName("search").setType("button").setLocator("\"button[name='QBF1']\"").setBy("id");
		Element e2 = new Element();
		e2.setName("save").setType("button").setLocator("\"button[name='QBF2']\"").setBy("css");
		
		ControlDataFunctionFactory fact = 
				new ControlDataFunctionFactory(
						Arrays.asList(
								new ControlDataValues(e1),
								new ControlDataValues(e2)
						), 
						new ExistingAnnotation("@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"07/01/2022\")").setIndent(1));
		
		assertEquals(expected, fact.getFunctionBuildMyControls().toString());
	}
	@Test
	void build_empty_ControlsFunction() throws InvalidArgumentException {
		String expected = 
				"\t\tprivate void buildMyControls() {}";
		
		ControlDataFunctionFactory fact = 
				new ControlDataFunctionFactory(null, null); 	
		assertEquals(expected, fact.getFunctionBuildMyControls().toString());
	}
}
