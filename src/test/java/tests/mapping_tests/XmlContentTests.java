/**
 * 
 */
package tests.mapping_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.SiteMapContentGetter;
import app.xml_content.PomMapperTest;
import app.xml_content.XmlContent;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.XmlContainer;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.ElementFunction;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.PomMapperApp;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataList;
import site_mapper.jaxb.pom.test_data.TestDataOut;
import site_mapper.jaxb.pom.test_data.TestDataText;

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
	
	private static XmlContent content;
	private static Module mod;
	private static MenuItem empDetails;
	private static Container body;
	
	@BeforeAll
	public static void setup() {
		content = contentGetter.getContent().get();
		mod = content.getModules().get(0);
		empDetails = mod.getMenus().get(0).getMenuItems().get(0);
//		body = empDetails.getBody().getItemContainer();	
	}
	
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
		assertEquals("payroll", mod.getName());
	}
	
//	@Test
//	void get_toolTip() {		
//		assertEquals("Save Record", 
//				empDetails.getFooter()
//									.getItemContainer()									
//									.getElements()
//									.get(0)
//									.getToolTipText());
//	}

	@Test
	void get_TestDataList_fromDataIn() {
		Container tabs =  body.getContainers().get(0);
		Container tabBasicDetails =  tabs.getContainers().get(0);
		Container grpGradeInput =  tabBasicDetails.getContainers().get(0);
		Element elGrade = grpGradeInput.getElements().get(0);
		TestDataIn testData =  (TestDataIn) elGrade.getTestDataIn(); 
		TestDataList data = (TestDataList) testData.getTestData();
		
		assertEquals("1,2,3", data.getValue());
	}

	@Test
	void getTestData_as_list_fromDataIn() {
		Container tabs =  body.getContainers().get(0);
		Container tabBasicDetails =  tabs.getContainers().get(0);
		Container grpGradeInput =  tabBasicDetails.getContainers().get(0);
		Element elGrade = grpGradeInput.getElements().get(0);
		TestDataIn testData =  (TestDataIn) elGrade.getTestDataIn(); 
		TestDataList data = (TestDataList) testData.getTestData();
		List<String> dataList = data.getAsList();
		
		assertEquals("3", dataList.get(2));
	}
	
	@Test
	void getTestData_insertClass_fromDataIn() {
		Container tabs =  body.getContainers().get(0);
		Container tabBasicDetails =  tabs.getContainers().get(0);
		Container grpGradeInput =  tabBasicDetails.getContainers().get(0);
		Element elGrade = grpGradeInput.getElements().get(0);
		TestDataIn testData =  (TestDataIn) elGrade.getTestDataIn(); 

		
		assertEquals("AnInsertClass", testData.getInsertWith());
	}
	
	@Test
	void getTestDataText_fromDataOut() {
		Container tabs =  body.getContainers().get(0);
		Container tabBasicDetails =  tabs.getContainers().get(0);
		Container grpGradeInput =  tabBasicDetails.getContainers().get(0);
		Element elGrade = grpGradeInput.getElements().get(0);
		TestDataOut testData =  elGrade.getTestDataOut(); 
		TestDataText data = (TestDataText) testData.getTestData();
		
		assertEquals("some text out", data.getValue());
	}
		
	@Test
	void mapTestObjects() {
		/**
		 * Implicit pass if no errors.
		 * Also check site_map.xml -> RootDir -> ParentPackage
		 * for the created classes.
		 */		
		PomMapperTest mapper = new PomMapperTest((new PomMapperTests()).new PomTestData(), content);
		mapper.createPomsFromSource(XML_SOURCE);
	}

//	@Test 
//	void elementFunction_withDefaultPass() {
//		Element e = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getFooter().getItemContainer().getElements().get(0);
//		ElementFunction func = e.getElementFunction().setType("button").setName("save");
//		
//		assertEquals(
//				"\n\t@TestControl(type=\"button\")\n" +
//				"\tpublic DynamicTest buttonSave () {\n" +
//				"\t\treturn DynamicTest.dynamicTest(\"[buttonSave]\", () -> fail(\"*NOT IMPLEMENTED*\"));\n" +
//				"\t}", 
//				func.toString()); 	
//	}

//	@Test 
//	void elementFunction_withoutDefaultPass() {
//		Element e = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getFooter().getItemContainer().getElements().get(0);
//		ElementFunction 
//			func = 
//				e.getElementFunction()
//						.setType("button")
//						.setName("save")
//						.isDefaultPass(false);
//		
//		assertEquals(
//				"\n\t@TestControl(type=\"button\")\n" +
//				"\tpublic DynamicTest buttonSave () {\n" +
//				"\t\treturn DynamicTest.dynamicTest(\"[buttonSave]\", () -> fail(\"*NOT IMPLEMENTED*\"));\n" +
//				"\t}", 
//				func.toString()); 	
//	}
//
//	@Test
//	void get_empLookup_from_headerContainer() {
//		XmlContainer header = content.getModules().get(0).getMenus().get(0).getMenuItems().get(0).getHeader().getItemContainer();
//		XmlContainer empLookup = header.getContainers().get(0);
//		assertEquals("EmpLookup", empLookup.getName());		
//	}
	
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
