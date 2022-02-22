/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.List;

import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.Locator;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface XmlContainer {
	String getType();
	String getName();
	Locator getLocator();
	List<Container> getContainers();
	List<Element> getElements();
}
