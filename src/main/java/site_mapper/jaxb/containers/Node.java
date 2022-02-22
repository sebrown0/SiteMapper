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
 */
public class Node {
	private Node prev;
	private String name;
	private int nodeLevel = 0;	//Root
	private int current = 0;
	private int numContainers;
	private boolean isIncludedInControlList;
	private List<Container> containers;	
	private List<Element> elements;
	
	private String elementStr = "";
	private String containerStr = "";
	
	public Node(Container container) {
		if(container != null) {
			this.name = container.getName();
			this.prev = null;		
			this.containers = container.getContainers();
			this.elements =  container.getElements();
			this.isIncludedInControlList = false;
			setNumContainers();
		}
	}
	public Node(Node prev, Container container, int nodeLevel, boolean isIncludedInControlList) {		
		if(container != null) {
			this.name = container.getName();
			this.prev = prev;
			this.nodeLevel = nodeLevel;
			this.containers = container.getContainers();
			this.elements =  container.getElements();
			this.isIncludedInControlList = isIncludedInControlList;
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
		return containers.get(current);
	}
	public int getNodeLevel() {
		return nodeLevel;
	}
	public String getName() {
		return name;
	}
//	public Node setName(String name) {
//		this.name = name;
//		return this;
//	}
	
	@Override
	public String toString() {
		String ret = "";
		if(elements != null) {
			for (Element e : elements) {
				ret += e.getElementString() + "\n";
				containerStr += e.getElementString() + "\n";
			}
		}
		
		if(containers != null) {			
			for (Container cnt : containers) {								
				ret += getIndent() + cnt.getName() + "\n";
			}	
		}
		
		return ret;
	}
	private String getIndent() {
		String res = "";
			for(int i=1; i <= nodeLevel; i++) {
				res += "  ";
			}
		return res;
	}
	public boolean isIncludedInControlList() {
		return isIncludedInControlList;
	}
}
