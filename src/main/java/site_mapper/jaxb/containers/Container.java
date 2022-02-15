/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.Locator;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="HeaderElements")
@XmlAccessorType(XmlAccessType.FIELD)
public class Container implements XmlContainer {
	@XmlAttribute(name="type")
	private String type;
	@XmlAttribute(name="name")
	private String name;
	@XmlElement(name="Locator")
	private Locator locator;
	@XmlElement(name="Container")
	private List<Container> containers;
	@XmlElement(name="Element")
	private List<Element> elements;
	
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public List<Container> getContainers() {
		return containers;
	}
	@Override
	public List<Element> getElements() {
		return elements;
	}
	@Override
	public Locator getloLocator() {
		return locator;
	}
	public Container setType(String type) {
		this.type = type;
		return this;
	}
	public Container setName(String name) {
		this.name = name;
		return this;
	}
	public Container setLocator(Locator locator) {
		this.locator = locator;
		return this;
	}
	public Container setContainers(List<Container> containers) {
		this.containers = containers;
		return this;
	}
	public Container setElements(List<Element> elements) {
		this.elements = elements;
		return this;
	}
	
//	public Optional<Container> getContainer(String contName) {
//		return 
//			containers.stream()
//				.filter(c -> c.name.equals(contName))
//				.findFirst();
//	}

//	public Optional<Container> getContainer(String contName) {
//		Node n = new Node(this);
//		Optional<Container> ret = Optional.empty();
//		while(ret.isEmpty()) {
//			n = getContainer(n, contName);
//		}
//		return ret; 			
//	}
//	
//	private Node getContainer(Node n, String contName) {
//		if(n != null) {
//			var name = n.getCurrentContainer().getName();
//			System.out.println("->" + name); // TODO - remove or log
//			if(name.equals(contName)) {
//				return n;
//			}else {
//				
//			}
//		}		
//		return null;
//	}
//	private Container getContainer(Container current, String contName) {
//		
////		while(current != null) { 
////			if(current.getName().equals(contName)) {
////				return current; 
////			}else {		
////				return getContainer(getNext(current), contName);
////			}
////		}
//		return null;
//	}
//	private Container getNext(Container c) {
////		if(c != null && idx <= c.getContainers().size()) {
////			return c.getContainers().get(idx-1);
////		}
//		return null;	
//	}
//	public Optional<Container> getContainer(String contName) {
//		return Optional.ofNullable(getContainer(containers.get(0), containers.get(0), 0, contName)); 			
//	}
//	private Container getContainer(Container current, Container currentLast, int idx, String contName) {
//		
////		Container c = (last != null) ? conts.get(0) : null;
////		Container currentLast = last;
//		if(current != null) { 
//			if(current.getName().equals(contName)) {
//				return current; 
//			}else {
//				idx++;
//				return getContainer(getNext(current, idx), current, idx, contName);
//			}
//		}else {
//			idx++;
//			return getContainer(currentLast, currentLast, idx, contName);	
//		}		
//		
//	}
	
//	private Container getNext(Container c, int idx) {
//		if(c != null && idx <= c.getContainers().size()) {
//			return c.getContainers().get(idx-1);
//		}
//		return null;	
//	}
//	private Container getContainer(List<Container> conts, String contName) {
//		Container c = (conts != null) ? conts.get(0) : null;
//		if(c != null) {
//			if(c.getName().equals(contName)) {
//				return c; 
//			}
//		}
//		return getContainer(c.getContainers(), contName);
//	}
	
//	public Optional<Container> getContainer(String contName) {
//		return Optional.ofNullable(getContainer(containers, contName)); 			
//	}
//	private Container getContainer(List<Container> conts, String contName) {
//		int idx = 0;
//		Container c = conts.get(idx);
//		while(idx < conts.size()) {
//			if(c.getName().equals(contName)) {
//				return c; 
//			}
//			else {
//				if(idx < conts.size()) {
//					c = conts.get(++idx);	
//				}				
//			}
//		}
//		idx++;
//		return getContainer(c.getContainers(), contName);
//	}
	
//	private Container getContainer(List<Container> containers, int idx, String contName) {
//		Container c = containers.get(idx);
//		while(c != null) {
//			if(c.getName().equals(contName)) {
//				return c; 
//			}
//			else {
//				c = containers.get(++idx);
//			}
//		}
//		return getContainer(c.getContainers(), ++idx, contName);
//	}
//	private Container getContainer(Container c, String contName) {
//		if(c.getName().equals(contName)) {
//			return c; 
//		}
//		return getContainer(c.getContainers().get(0), contName);		
//	}
}
