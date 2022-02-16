/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.ContainerFinder;
import site_mapper.jaxb.containers.Node;
import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class ContainerTests {
	private static Container root;
	
	@BeforeAll
	static void setup() {
		Element empCode = new Element().setName("code");
		
		Container level_2_A= 
			new Container()
						.setName("level_2_A")
						.setType("InputGroup")
						.setElements(List.of(empCode));
		
		Container level_1_A = 
			new Container()
						.setName("level_1_A")
						.setType("InputGroup")
						.setContainers(List.of(level_2_A))
						.setElements(List.of(empCode));
		
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
	
	@Test
	void getNextContainer() {
		Node rootNode = new Node(null, root);
		Container one = rootNode.getNextContainer();
		assertEquals(one.getName(), "level_1_A");
		Container two = rootNode.getNextContainer();
		assertEquals(two.getName(), "level_1_B");
		Container none = rootNode.getNextContainer();
		assertTrue(none == null);
	}
	
	@Test
	void getNextContainer_from_containerFinder() {
		Node rootNode = new Node(null, root);
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
	void get_empLookupName_from_header() {
//		Optional<Container> emp = root.getContainer("EmpLookup");
//		assertEquals("EmpLookup", emp.get().getName());
	}

	@Test
	void get_inEmpLookup_from_header() {
//		Node node = new Node(root);
//		ContainerFinder finder = new ContainerFinder(node);
//		finder.getContainer("level_2_C");
//		Optional<Container> inEmpLookup = root.getContainer("level_2_C");
//		assertEquals("level_2_C", inEmpLookup.get().getName());
	}
}
