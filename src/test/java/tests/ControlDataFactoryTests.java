/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

import exceptions.InvalidArgumentException;
import file.class_file.body.ControlBuilder;
import site_mapper.creators.control_data.ControlDataFunction;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.menu_items.TestElement;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.Locator;
import site_mapper.jaxb.pom.SiteMapInfo;

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

//	@Test
//	void incorrectControlName() {		
//		Element e = new Element();
//		e.setName(null).setType(null).setLocator(null).setBy("css");
//		
//		Exception ex = assertThrows(
//			InvalidArgumentException.class, 
//			() -> ControlDataFunctionFactory.getControlData(new ControlDataValues(e)));
//		assertEquals("[null] is not a valid control type name.", ex.getMessage());
//	}
//	@Test
//	void incorrectByValue() {
//		Element e = new Element();
//		e.setName("button").setType(null).setLocator(null).setBy(null);
//		
//		Exception ex = assertThrows(
//			InvalidArgumentException.class, 
//			() -> ControlDataFunctionFactory.getControlData(new ControlDataValues(e)));
//		assertEquals("[null] is not a valid By type name.", ex.getMessage());
//	}
//	@Test
//	void check_Button_with_CSS() throws InvalidArgumentException {
//		Element e = new Element();
//		e.setName("search").setType("button").setLocator("\"button[name='QBF1']\"").setBy("css");
//		
//		String s = ControlDataFunctionFactory.getControlData(new ControlDataValues(e)).get();
//		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"\"button[name='QBF1']\"\")))", s);
//	}
//	@Test
//	void check_Button_with_XPATH() throws InvalidArgumentException {
//		Element e = new Element();
//		e.setName("search").setType("button").setLocator("\"button[name='QBF1']\"").setBy("xpath");
//		
//		String s = ControlDataFunctionFactory.getControlData(new ControlDataValues(e)).get();
//		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.xpath(\"\"button[name='QBF1']\"\")))", s);
//	}
//	@Test
//	void check_Button_with_ID() throws InvalidArgumentException {
//		Element e = new Element();
//		e.setName("search").setType("button").setLocator("\"button[name='QBF1']\"").setBy("id");
//		
//		String s = ControlDataFunctionFactory.getControlData(new ControlDataValues(e)).get(); 	
//		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.id(\"\"button[name='QBF1']\"\")))", s);		 	
//	}
	@Test
	void buildControlsFunction() throws InvalidArgumentException {
		String expected = 
				"\t@SiteMap(author=\"SteveBrown\", version=\"1.0.0\", date=\"" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\")\n" +
				"\tprivate void buildMyControls() {\n" +
				"\t\tInputGroup grpEmpLookup = new InputGroup(coreData, By.cssSelector(\"div[class='input-group']\"));\n" +
				"\t\tgrpEmpLookup\n" +
				"\t\t\t.addElement(\"code\", By.cssSelector(\"input[id='FORM_ID']\"));\n" +
				"\t\tvar myControls =\n" +
				"\t\t\tList.of(" +
				"\n\t\t\t\tnew ControlData(\"EmpLookup\", new ControlGetterInputGroup(coreData, grpEmpLookup))" +
				
				"\n\t\t\t);\n\t\tsuper.buildPanelControls(myControls);\n\t}";
		
       
		Element code = 
			new Element()
					.setName("code")
					.setType("text_out")
					.setBy("css")
					.setLocator("\"input[id='FORM_ID']\"");				
		
		Container empLookup = 
			new Container()
					.setName("EmpLookup")
					.setType("InputGroup")
					.setLocator(
							new Locator()
									.setBy("css")
									.setLocator("div[class='input-group']"))
					.setElements(List.of(code));
		
		Container header = 
			new Container()
					.setName("header")
					.setContainers(List.of(empLookup));
		
		TestElement clazz = new MenuItem();
		clazz.setSiteMapInfo(
			new SiteMapInfo()
					.setAuthor("SteveBrown")
					.setVersion("1.0.0"))
			.setHeaderContainer(header);
		
		ControlBuilder builder = new ControlBuilder((ElementClass) clazz);
		ControlDataFunction function = builder.buildControlFunction();

		assertEquals(expected, function.toString());
	}
//	@Test
//	void build_empty_ControlsFunction() throws InvalidArgumentException {
//		String expected = 
//				"\t\tprivate void buildMyControls() {}";
//		
//		ControlDataFunctionFactory fact = 
//				new ControlDataFunctionFactory(null, null); 	
//		assertEquals(expected, fact.getFunctionBuildMyControls().toString());
//	}
}
