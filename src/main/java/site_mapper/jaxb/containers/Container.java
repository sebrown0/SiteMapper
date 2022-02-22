/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.control_data.ContainerStringGetter;
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
	
	public boolean isParentContiner() {
		return (containers != null && containers.size() > 0) ? true : false;
	}
	
	public String getContainerString() {
		return ContainerStringGetter.getStringFrom(this);
	}
	
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
	public Locator getLocator() {
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
	
}
