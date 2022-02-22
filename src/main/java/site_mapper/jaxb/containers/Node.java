/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.List;

import file.helpers.Formatter;
import site_mapper.jaxb.pom.Element;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Node {
	private Node prev;
	private String name;
	private String type;
	private String locStr;
	private int nodeLevel = 0;	//Root
	private int current = 0;
	private int numContainers;
	private boolean isIncludedInControlList;
	private List<Container> containers;	
	private List<Element> elements;
			
	public Node(Container container) {	
		initialise(container);				
	}
	public Node(Node prev, Container container, int nodeLevel, boolean isIncludedInControlList) {	
		this.prev = prev;
		this.nodeLevel = nodeLevel;
		this.isIncludedInControlList = isIncludedInControlList;
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
		return containers.get(current);
	}
	public int getNodeLevel() {
		return nodeLevel;
	}
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String elementStr = "";
		String incElements = "";
		String incContainers = "";

		String grpStr = String.format(
				"\t\tControlGetterGroup %s =\n\t\t\tnew ControlGetter%s" +
				"(\"%s\", coreData, %s\n\t\t\t\t.addControls(Arrays.asList(", 
				StringUtils.camelCase(name), 
				StringUtils.pascalCase(type), 
				StringUtils.pascalCase(name), 
				locStr);
		
		if(containers != null) {
			for (Container cnt : containers) {
				incContainers += cnt.getName() + ", ";						
			}	
		}
		
		if(elements != null) {
			for (Element e : elements) {
				elementStr += e.getElementString() + "\n";
				incElements += e.getElementName() + ", ";
			}	
//			StringUtils.removeTrailingComma(incElements);
		}

		grpStr += StringUtils.removeTrailingComma(incContainers) + StringUtils.removeTrailingComma(incElements) + "));";
		return elementStr + grpStr;
	}
	
	public boolean isIncludedInControlList() {
		return isIncludedInControlList;
	}
}
