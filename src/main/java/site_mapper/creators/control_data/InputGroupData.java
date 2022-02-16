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
public class InputGroupData implements ControlData {
	private Container cont;
	private String name;
	private String grp;
	
	public InputGroupData(Container cont) {
		if(cont != null) {
			this.cont = cont;
			setName(cont.getName());
			setInitialData();
			setElements();
		}		
	}

	private void setName(String grpName) {
		name = grpName;
	}
	private void setInitialData() {
		grp = String.format(
				"InputGroup grp%s = " +
				"new InputGroup(coreData, By.cssSelector(\"div[class='input-group']\"));\n\tgrp%s", 
				name, name);				
	}
	
	private void setElements() {
		List<Element> elements = cont.getElements();
		elements.forEach(e -> {
			addElement(e);
		});
	}
	
	private void addElement(Element e) {
		String s = 
				String.format("\n\t\t.addElement(\"%s\", By.cssSelector(\"%s(%s)\"))", 
				e.getElementName(), 
				ByLocatorTypeFactory.getByType(e.getByLocatorType()),
				e.getByLocatorValue());
		grp += s;
	}
	
	@Override
	public String getValue() {		
		return grp + ";";
	}	

}
