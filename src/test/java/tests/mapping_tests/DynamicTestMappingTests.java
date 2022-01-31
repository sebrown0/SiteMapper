/**
 * 
 */
package tests.mapping_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import app.SiteMapContentGetter;
import app.xml_content.DynamicTestApp;
import app.xml_content.DynamicTestMapper;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Tests to see that the Dynamic Test info is 
 * unmarshalled correctly.
 */
class DynamicTestMappingTests {
	private static final String XML_SOURCE = 
	"./src/test/resources/site_map/site_map.xml";

	@Test
	void getContentFrom_DynamicTestApp() {
		SiteMapContentGetter<DynamicTestApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);		
		Optional<DynamicTestApp> content = contentGetter.getContent(DynamicTestApp.class);
		
		assertFalse(content.isEmpty());
	}

	@Test
	void getInfoFrom_DynamicTestApp() {
		SiteMapContentGetter<DynamicTestApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);		
		Optional<DynamicTestApp> content = contentGetter.getContent(DynamicTestApp.class);
		SiteMapInfo info = content.get().getSiteMapInfo();
		
		assertEquals(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()), info.getDate());
	}
	
	@Test
	void getModuleFrom_DynamicTestApp() {
		SiteMapContentGetter<DynamicTestApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);		
		Optional<DynamicTestApp> content = contentGetter.getContent(DynamicTestApp.class);
		List<Module> modules = content.get().getModules();
		
		assertTrue(modules.size()>0);
		assertEquals("payroll",modules.get(0).getName());
	}

	@Test
	void getincludeElementsForTestFrom_DynamicTestApp() {
		SiteMapContentGetter<DynamicTestApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);		
		Optional<DynamicTestApp> content = contentGetter.getContent(DynamicTestApp.class);
		List<String> elements = content.get().getIncludeElementsForTest();
		
		assertTrue(elements.size()>0);
		assertEquals("button",elements.get(0));
	}
	
	@Test
	void getincludeElementsForTestFrom_DynamicTestMapper() {				
		Optional<DynamicTestApp> content = DynamicTestMapper.getDynamicTestContent(XML_SOURCE);
		List<String> elements = content.get().getIncludeElementsForTest();
		
		assertTrue(elements.size()>0);
		assertEquals("button",elements.get(0));
	}
}
