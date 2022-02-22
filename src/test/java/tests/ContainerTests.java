/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.ContainerFinder;
import site_mapper.jaxb.containers.Node;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.Locator;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class ContainerTests {
	private static Container root;
	private static Container level_1_A;
	private static Locator level_1_ALoc = new Locator().setBy("css").setLocator("a[id='LEVEL_1_A']");
	
	@Test
	void jjjjjjjjjj() {
		final String RESULT = "";
		
		Locator empLoc = new Locator().setBy("css").setLocator("input[id='FORM_ID']");
		Element empCode = new Element().setName("IdCardNo").setType("input").setLocator(empLoc);
		Container basicDetails = 
			new Container()
				.setName("BasicDetails")
				.setType("Tab")
				.setElements(List.of(empCode));
	
		
		Locator dateTextLoc = new Locator().setBy("css").setLocator("a[id='elA']");		
		Element dateText = new Element().setName("DateText").setType("Input").setLocator(dateTextLoc);
		Locator calLoc = new Locator().setBy("css").setLocator("a[id='CAL']");		
		Element cal = new Element().setName("Date").setType("Date").setLocator(calLoc);
		
		Locator datePickerLoc = new Locator().setBy("css").setLocator("a[id='DATE_PICK']");		
		Container datePicker = 
				new Container()
					.setName("DatePicker")
					.setType("InputGroup")
					.setLocator(datePickerLoc)
					.setElements(List.of(dateText, cal));
		
		Locator elALoc = new Locator().setBy("css").setLocator("a[id='elA']");		
		Element elA = new Element().setName("EL_A_IN_A").setType("Input").setLocator(elALoc);
		Locator elBLoc = new Locator().setBy("css").setLocator("a[id='elB']");		
		Element elB = new Element().setName("EL_B_IN_A").setType("Input").setLocator(elBLoc);
		Locator contALoc = new Locator().setBy("css").setLocator("a[id='tab-tab1']");
				
		Container elACont = 
				new Container()
					.setName("TAB_A")
					.setType("Tab")
					.setLocator(contALoc)
					.setContainers(List.of(datePicker))
					.setElements(List.of(elA, elB));
		
		Locator tabsLoc = new Locator().setBy("css").setLocator("ul[class='nav nav-tabs']");
		Container tabs = 
				new Container()
					.setName("Tabs")
					.setType("Tabs")
					.setLocator(tabsLoc)
					.setContainers(List.of(basicDetails, elACont));
		
		Container footer = 
				new Container()
					.setName("Footer")
					.setElements(List.of(elB));
		
		Container root = 
				new Container()
					.setName("ROOT")
					.setContainers(List.of(tabs, footer));
		
		Node rootNode = new Node(root);
		ContainerFinder finder = new ContainerFinder(rootNode);

		System.out.println(finder.traverseTree().getControlDataFunction()); // TODO - remove or log 	
	}

	@BeforeAll
	static void setup() {
		Element empCode = new Element().setName("code");
		Element save = 
			new Element()
				.setName("save")
				.setType("button")
				.setLocator(new Locator().setBy("css").setLocator("div[class='SAVE']"));
		
		Container level_2_A= 
			new Container()
						.setName("level_2_A")
						.setType("InputGroup")
						.setElements(List.of(empCode));
		
		level_1_A = 
			new Container()
						.setName("level_1_A")
						.setType("InputGroup")
						.setLocator(level_1_ALoc)
						.setContainers(List.of(level_2_A))
						.setElements(List.of(empCode, save));
		
		Container level_2_B= 
				new Container()
							.setName("level_2_B")
							.setType("InputGroup");
		
		Container level_2_C= 
				new Container()
							.setName("level_2_C")
							.setType("InputGroup");
		
		Container level_1_B = 
				new Container()
							.setName("level_1_B")
							.setType("InputGroup")
							.setContainers(List.of(level_2_B, level_2_C));
							
		root = 
			new Container()
						.setName("root")
						.setType("container")
						.setContainers(List.of(level_1_A, level_1_B));		
	}
	
//	@Test
//	void groupString_fromContainer() {
//		assertEquals(
//				"\t\t/* Controls in group[level_1_A] */" +
//				"\n\t\tControlGetter code =" +
//				"\n\t\t\tnew ControlGetter(\"Code\", coreData);" +
//				"\n\t\tControlGetter save =" +
//				"\n\t\t\tnew ControlGetterButton(\"Save\", coreData, By.cssSelector(\"div[class='SAVE']\"));" +
//				"\n\t\t/* Control group [level_1_A] */" +
//				"\n\t\tControlGetterGroup level_1_A =" +
//				"\n\t\t\tnew ControlGetterInputGroup(\"Level_1_A\", coreData, By.cssSelector(\"a[id='LEVEL_1_A']\")" +
//				"\n\t\t\t\t.addControls(Arrays.asList(code, save));", 
//				level_1_A.getContainerString());
//	}
	
	@Test
	void getNextContainer() {
		Node rootNode = new Node(root);
		Container one = rootNode.getNextContainer();
		assertEquals(one.getName(), "level_1_A");
		Container two = rootNode.getNextContainer();
		assertEquals(two.getName(), "level_1_B");
		Container none = rootNode.getNextContainer();
		assertTrue(none == null);
	}
	
	@Test
	void getNextContainer_from_containerFinder() {
		Node rootNode = new Node(root);
		ContainerFinder finder = new ContainerFinder(rootNode);
		
		Container one = finder.getNextContainer();
		assertEquals("level_1_A", one.getName());
		Container two = finder.getNextContainer();
		assertEquals("level_2_A", two.getName());
		Container three = finder.getNextContainer();
		assertEquals("level_1_B", three.getName());
		Container four = finder.getNextContainer();
		assertEquals("level_2_B", four.getName());
		Container five = finder.getNextContainer();
		assertEquals("level_2_C", five.getName());
		Container none = finder.getNextContainer();
		assertTrue(none == null);
	}
	
	@Test
	void getContainerLevel_2_B() {
		Node rootNode = new Node(root);
		ContainerFinder finder = new ContainerFinder(rootNode);
		Container lvl_2_b = finder.findContainer("level_2_B");
		assertEquals("level_2_B", lvl_2_b.getName());
	}

	@Test
	void getNonExistantContainer() {
		Node rootNode = new Node(root);
		ContainerFinder finder = new ContainerFinder(rootNode);
		Container none = finder.findContainer("XXXX");
		assertTrue(none == null);
	}
	
}
