/**
 * 
 */
package tests.mapping_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import app.SiteMapContentGetter;
import app.xml_content.PomMapperTest;
import app.xml_content.XmlContent;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.ElementFunction;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.PomMapperApp;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class XmlContentTests {	
	private static final String XML_SOURCE = 
	"./src/test/resources/site_map/site_map.xml";
	
	@Test
	void getContentObj() {
		XmlContent content = new PomMapperApp();
		assertTrue(content != null);
	}

	@Test
	void getContentFromPomMapper() {
		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);		
		Optional<PomMapperApp> content = contentGetter.getContent(PomMapperApp.class);
		
		assertFalse(content.isEmpty());
	}

	@Test
	void getContentModulePomMapper() {
		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);
		XmlContent content = contentGetter.getContent(PomMapperApp.class).get();
		Module mod = content.getModules().get(0);
		
		assertEquals("payroll", mod.getName());
	}
	
	@Test
	void get_toolTip() {
		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);
		XmlContent content = contentGetter.getContent(PomMapperApp.class).get();
		Module mod = content.getModules().get(0);
		MenuItem empDetails = mod.getMenus().get(0).getMenuItems().get(0);
		
		assertEquals("Save Record", empDetails.getFooterElements().get(0).getToolTipText());
	}
	
	@Test
	void mapTestObjects() {
		/*
		 * Implicit pass if no errors.
		 * Also check site_map.xml -> RootDir -> ParentPackage
		 * for the created classes.
		 */
		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);
		XmlContent content = contentGetter.getContent(PomMapperApp.class).get();
		PomMapperTest mapper = new PomMapperTest((new PomMapperTests()).new PomTestData(), content);
		mapper.createPomsFromSource(XML_SOURCE);
	}

	@Test 
	void elementFunction_withDefaultPass() {
		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);
		XmlContent content = contentGetter.getContent(PomMapperApp.class).get();
		Element e = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getFooterElements().get(0);
		ElementFunction func = e.getElementFunction().setType("button").setName("save");
		
		assertEquals(
				"\n\t@TestControl(type=\"button\")\n" +
				"\tpublic DynamicTest buttonSave () {\n" +
				"\t\treturn DynamicTest.dynamicTest(\"[buttonSave] *NOT IMPLEMENTED*\", () -> assertTrue(true));\n" +
				"\t}", 
				func.toString()); 	
	}

	@Test 
	void elementFunction_withoutDefaultPass() {
		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);
		XmlContent content = contentGetter.getContent(PomMapperApp.class).get();
		Element e = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getFooterElements().get(0);
		ElementFunction 
			func = 
				e.getElementFunction()
						.setType("button")
						.setName("save")
						.isDefaultPass(false);
		
		assertEquals(
				"\n\t@TestControl(type=\"button\")\n" +
				"\tpublic DynamicTest buttonSave () {\n" +
				"\t\treturn DynamicTest.dynamicTest(\"[buttonSave]\", () -> fail(\"*NOT IMPLEMENTED*\"));\n" +
				"\t}", 
				func.toString()); 	
	}

//	@Test
//	void XXXXXXXXXXXXXXX() {
//		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);
//		XmlContent content = contentGetter.getContent(PomMapperApp.class).get();
//		Module mod = content.getModules().get(0);
//		ElementClass item = mod.getMenus().get(0).getMenuItems().get(0);
//		List<ElementFunction> funcs = item.getElementFunctions();
////		funcs.forEach(f -> System.out.println(String.format("ANNOTATION\n%s", f.toString())));
//		 	
//		funcs.forEach(f -> {
//			String m = new DynamicTestMethodBuilder(f, new SiteMapInfo().setAuthor("SteveBrown").setVersion("1.0.0")).build();
//			System.out.println(m); // TODO - remove or log 	
//		});
//		
////		assertEquals("payroll", mod.getName());
//	}	
}
