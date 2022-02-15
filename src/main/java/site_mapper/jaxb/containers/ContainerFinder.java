/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.Optional;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ContainerFinder {
	private Node root;

	public ContainerFinder(Node root) {
		this.root = root;
	}
	
	public Optional<Container> getContainer(String contName) {
		System.out.println("->" + root.getCurrentContainer().getName()); // TODO - remove or log 	
		return null;
	}
}
