/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.List;

import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Node in the tree of containers from the XML file.
 */
public class Node {
	private Node prev;
	private String name;
	private String type;
	private String locStr;
	private int current = 0;
	private int numContainers;
	private boolean isIncludedInControlList;
	private List<Container> containers;	
	private List<Element> elements;
			
	public Node(Container container) {	
		initialise(container);
		isIncludedInControlList = true;
	}

	public Node(Node prev, Container container) {
		this.prev = prev;

		initialise(container);
	}
	
	private void initialise(Container container) {
		if(container != null) {
			name = container.getName();
			type = container.getType();
			locStr = container.getLocatorStr();
			containers = container.getContainers();
			elements =  container.getElements();
			
			setNumContainers();			
		}
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
		return (containers != null) ? containers.get(current) : null;
	}	
	public boolean isIncludedInControlList() {
		return isIncludedInControlList;
	}
	public String getName() {
		return name;
	}	
	public String getType() {
		return type;
	}
	public String getLocStr() {
		return locStr;
	}
	public List<Container> getContainers() {
		return containers;
	}
	public List<Element> getElements() {
		return elements;
	}

}