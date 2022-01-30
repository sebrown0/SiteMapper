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
}
