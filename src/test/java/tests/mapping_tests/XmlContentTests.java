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
import site_mapper.elements.ElementDataList;
import site_mapper.elements.ElementDataText;
import site_mapper.elements.TestDataGetter;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.XmlContainer;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.ElementFunction;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.PomMapperApp;
import site_mapper.jaxb.pom.TestDataIn;
import site_mapper.jaxb.pom.TestDataList;
import site_mapper.jaxb.pom.TestDataText;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * TODO
 * These tests need updating to fit with the 
 * how ContainerTree works.
 */
class XmlContentTests {	
	private static final String XML_SOURCE = 
	"./src/test/resources/site_map/site_map.xml";
	
	private static final SiteMapContentGetter<PomMapperApp> contentGetter = 
			new SiteMapContentGetter<>(XML_SOURCE, PomMapperApp.class);		
	
	@Test
	void getContentObj() {
		XmlContent content = new PomMapperApp();
		assertTrue(content != null);
	}

	@Test
	void getContentFromPomMapper() {		
		Optional<PomMapperApp> content = contentGetter.getContent();
		
		assertFalse(content.isEmpty());
	}

	@Test
	void getContentModulePomMapper() {
		XmlContent content = contentGetter.getContent().get();
		Module mod = content.getModules().get(0);
		
		assertEquals("payroll", mod.getName());
	}
	
	@Test
	void get_toolTip() {
		XmlContent content = contentGetter.getContent().get();
		Module mod = content.getModules().get(0);
		MenuItem empDetails = mod.getMenus().get(0).getMenuItems().get(0);
		
		assertEquals("Save Record", empDetails.getFooter().getContainers().get(0).getElements().get(0).getToolTipText());
	}

	@Test
	void xxxxxxxxxxxxxxxxxxxxxxxxx() {
		XmlContent content = contentGetter.getContent().get();
		Module mod = content.getModules().get(0);
		MenuItem empDetails = mod.getMenus().get(0).getMenuItems().get(0);
		Container body = empDetails.getBody();
		Container tabs =  body.getContainers().get(0);
		Container tabBasicDetails =  tabs.getContainers().get(0);
		Container grpGradeInput =  tabBasicDetails.getContainers().get(0);
		Element elGrade = grpGradeInput.getElements().get(0);
//		TestDataGetter testDataGetter = elGrade.getTestDataIn();		
//		ElementDataText testData = (ElementDataText) testDataGetter.getTestData();
		TestDataIn testData =  (TestDataIn) elGrade.getTestData();
//			
//		TestDataText data = (TestDataText) testData.getValue(); 
		TestDataList data = (TestDataList) testData.getValue();
		
		System.out.println(data.getValue());
//		assertEquals("some test data in", testData.getValue());
		assertTrue(testData != null);
	}
	
	@Test
	void get_testData_In() {
		XmlContent content = contentGetter.getContent().get();
		Module mod = content.getModules().get(0);
		MenuItem empDetails = mod.getMenus().get(0).getMenuItems().get(0);
		Container body = empDetails.getBody();
		Container tabs =  body.getContainers().get(0);
		Container tabBasicDetails =  tabs.getContainers().get(0);
		Container grpGradeInput =  tabBasicDetails.getContainers().get(0);
		Element elGrade = grpGradeInput.getElements().get(0);
		TestDataGetter testDataGetter = elGrade.getTestDataIn();		
		ElementDataText testData = (ElementDataText) testDataGetter.getTestData();
		
		assertEquals("some test data in", testData.getText());
	}
	
	@Test
	void get_testDataList_from_out() {
		XmlContent content = contentGetter.getContent().get();
		Module mod = content.getModules().get(0);
		MenuItem empDetails = mod.getMenus().get(0).getMenuItems().get(0);
		Container body = empDetails.getBody();
		Container tabs =  body.getContainers().get(0);
		Container tabBasicDetails =  tabs.getContainers().get(0);
		Container grpGradeInput =  tabBasicDetails.getContainers().get(0);
		Element elGrade = grpGradeInput.getElements().get(0);
		TestDataGetter testDataGetter = elGrade.getTestDataOut();
		ElementDataList testData = (ElementDataList) testDataGetter.getTestData();
		
		assertEquals("item_1", testData.getData().get(0));
	}
		
	@Test
	void mapTestObjects() {
		/*
		 * Implicit pass if no errors.
		 * Also check site_map.xml -> RootDir -> ParentPackage
		 * for the created classes.
		 */
		XmlContent content = contentGetter.getContent().get();
		PomMapperTest mapper = new PomMapperTest((new PomMapperTests()).new PomTestData(), content);
		mapper.createPomsFromSource(XML_SOURCE);
	}

	@Test 
	void elementFunction_withDefaultPass() {
		XmlContent content = contentGetter.getContent().get();
		Element e = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getFooter().getContainers().get(0).getElements().get(0);
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
		XmlContent content = contentGetter.getContent().get();
		Element e = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getFooter().getContainers().get(0).getElements().get(0);
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

	@Test
	void get_empLookup_from_headerContainer() {
		XmlContent content = contentGetter.getContent().get();
		XmlContainer header = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getHeader();
		XmlContainer empLookup = header.getContainers().get(0);
		assertEquals("EmpLookup", empLookup.getName());		
	}
	
//	@Test
//	void get_elements_fromEmpLookup() {
//		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);
//		XmlContent content = contentGetter.getContent(PomMapperApp.class).get();
//		Container header = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getHeader();
//		Node rootNode = new Node(header);
//		SiteMapInfo info = new SiteMapInfo()
//				.setAuthor("SteveBrown")
//				.setVersion("1.0.0")
//				.setXmlSource("")
//				.setRootDir("C:/Users/SteveBrown/eclipse-workspace/2021/SiteMapper")
//				.setElementLibrary("C:/Users/SteveBrown/eclipse-workspace/2021/DTest")
//				.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
//				.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
//		ContainerTree tree = new ContainerTree(null, info, null, rootNode);
//		Container current = tree.getNextContainer();
//		final List<String> elementNames = List.of("code","EmpList", "Combos", "GridView", "Documents");
//		if(current != null) {
//			List<Element> elements = current.getElements();
//			elements.forEach(e -> {				
//				assertTrue(elementNames.contains(e.getElementName()));
//			});			
//		}else {
//			fail("Not current container");
//		}
//	}

}
