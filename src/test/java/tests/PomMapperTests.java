package tests;

import org.junit.jupiter.api.Test;

import app.PomMapper;

class PomMapperTests {		
	@Test
	void createPomsFromXML() {		
		final String XML_SOURCE = 
				"C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml";
		
		PomMapper mapper = new PomMapper(XML_SOURCE);
		mapper.createPoms();
	}
		
}
