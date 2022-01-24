/**
 * 
 */
package file.class_file.body;

import java.util.List;

import file.annotation.NewAnnotation;
import site_mapper.elements.Element;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Take a list of elements and create the 
 * 'method' for building controls in the class body.
 * 
 * The elements come from the XML file 
 * [MenuItem.elements] or [NewTestClassFileBuilder.elements]
 * and are of type [ElementCreation].
 * 
 */
public class ControlBuilder {
	private List<Element> elements;
	private SiteMapInfo info;
	private NewAnnotation annotation;
	
	public ControlBuilder(ElementClass clazz) {
		elements = clazz.getElements();
		info = clazz.getSiteMapInfo();

		buildControlFunction();
	}
	
	private void buildControlFunction() {
		setAnnotation();
		for (ElementCreation e : elements) {
			System.out.println(e.getElementName()); // TODO - remove or log 	
		}
	}

	private void setAnnotation() {
		annotation = new NewAnnotation(info, 1);
	}
	
	public NewAnnotation getAnnotation() {
		return annotation;
	}
}
