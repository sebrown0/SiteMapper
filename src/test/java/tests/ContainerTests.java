/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	void get_empLookupName_from_header() {
//		Optional<Container> emp = root.getContainer("EmpLookup");
//		assertEquals("EmpLookup", emp.get().getName());
	}

	@Test
	void get_inEmpLookup_from_header() {
		Node node = new Node(root);
		ContainerFinder finder = new ContainerFinder(node);
		finder.getContainer("level_2_C");
//		Optional<Container> inEmpLookup = root.getContainer("level_2_C");
//		assertEquals("level_2_C", inEmpLookup.get().getName());
	}
}
