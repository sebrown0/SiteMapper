package tests;

import org.junit.jupiter.api.Test;

import app.PomMapper;

class PomMapperTests {
	static final String XML_SOURCE = 
			"C:/Users/SteveBrown/eclipse-workspace/2021/SiteMapper/src/main/resources/site_map/site_map.xml";
	
//	@Test
//	void isOverwriting() {
//		PomMapper mapper = new PomMapper(XML_SOURCE);
//		assertTrue(mapper.get);
//	}
	
	@Test
	void createPomsFromXML() {		
		PomMapper mapper = new PomMapper(XML_SOURCE);
		mapper.createPoms();
	}
		
}
