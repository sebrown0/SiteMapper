/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.List;

import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class GroupControlData {
	private Container container;	
	private String name;
	private GroupControlData next;
	private List<Element> elements;
	
	public GroupControlData(Container container) {
		this.container = container;
		initialise();		
	}
	
	private void initialise() {
		if(container != null) {
			setName();
			setElements();
		}
	}
	
	private void setName() {		
		name = container.getName();
	}	
	private void setElements() {		
		elements = container.getElements();
	}

	public void setNext(GroupControlData next) {
		this.next = next;
	}
	public GroupControlData getNext() {
		return next;
	}
	
	public String getName() {
		return name;
	}	

	public List<Element> getElements() {
		return elements;
	}

	
	
}
