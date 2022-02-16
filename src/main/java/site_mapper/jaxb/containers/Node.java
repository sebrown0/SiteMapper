/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Node {
	private Node prev;
	private int current = 0;
	private int numContainers;
	private List<Container> containers;	
	
	public Node(Node prev, Container container) {
		this.prev = prev;
		this.containers = container.getContainers();
		
		setNumContainers();
	}

	private void setNumContainers() {
		if(containers != null) {
			numContainers = containers.size();
		}
	}
	
	public Node getPrev() {
		return prev;
	}
	
	public boolean hasAnotherContainer() {
		return (current < numContainers) ? true : false;
	}
	public Container getNextContainer() {
		Container ret = null;
		if(hasAnotherContainer()) {			
			ret = containers.get(current);
			current++;
		}
		return ret;
	}
	public Container getCurrentContainer() {
		return containers.get(current);
	}
}
