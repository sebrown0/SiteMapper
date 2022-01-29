/**
 * 
 */
package tests.mapping_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import app.PomMapper;
import app.xml_content.TestMapper;
import app.xml_content.XmlContent;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.PomMapperApp;
import tests.mapping_tests.PomMapperTests.PomTestData;

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
		PomMapper mapper = new PomMapper(XML_SOURCE);
		Optional<PomMapperApp> content = mapper.getContent();
		assertFalse(content.isEmpty());
	}
	
	@Test
	void getContentModulePomMapper() {
		PomMapper mapper = new PomMapper(XML_SOURCE);
		XmlContent content = mapper.getContent().get();
		Module mod = content.getModules().get(0);
		assertEquals("payroll", mod.getName());
	}
	
	@Test
	void mapTestObjects() {
		PomMapper pomMapper = new PomMapper(XML_SOURCE);
		XmlContent content = pomMapper.getContent().get();
		TestMapper mapper = new TestMapper((new PomMapperTests()).new PomTestData(), content);
		mapper.createTestPoms(XML_SOURCE);
	}
}
