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

}
