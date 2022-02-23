package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import file.imports.ControlImportGetter;
import file.imports.Import;
import site_mapper.jaxb.pom.SiteMapInfo;

class ControlImportGetter_Tests {
	private final static SiteMapInfo GOOD_INFO = 
		new SiteMapInfo()			
			.setElementLibrary("C:/Users/SteveBrown/eclipse-workspace/2021/DTest");
	
	private final static SiteMapInfo BAD_INFO = 
		new SiteMapInfo()			
			.setElementLibrary("");
	
	@Test
	void validGroup() {		
		Import res = ControlImportGetter.getImportForContolGetter("Group", GOOD_INFO);
		assertEquals("import control_builder.control_getters.group.ControlGetterGroup;\n", res.toString());
	}

	@Test
	void validTab() {		
		Import res = ControlImportGetter.getImportForContolGetter("Tab", GOOD_INFO);
		assertEquals("import control_builder.control_getters.group.ControlGetterTab;\n", res.toString());
	}
	
	@Test
	void invalidTab() {		
		Import res = ControlImportGetter.getImportForContolGetter("Tab", BAD_INFO);
		assertEquals("/* Placeholder for missing import [ControlGetterTab] */\n", res.toString());
	}


}
