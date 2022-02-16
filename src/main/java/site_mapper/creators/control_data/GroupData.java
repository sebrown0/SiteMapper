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
public abstract class GroupData implements ControlData {
	protected String name;
	protected String varName;
	protected String grp;
	
	private Container cont;	
	
	public GroupData(Container cont) {
		if(cont != null) {
			this.cont = cont;
			setName(cont.getName());
			setVarName();
			setInitialData();
			setElements();
		}		
	}

	private void setName(String grpName) {
		name = grpName;		
	}
	
	protected abstract void setInitialData();
	protected abstract void setVarName();
	
	private void setElements() {
		List<Element> elements = cont.getElements();
		elements.forEach(e -> {
			addElement(e);
		});
	}
	
	private void addElement(Element e) {
		String s = 
				String.format("\n\t\t\t.addElement(\"%s\", %s(%s)\"))", 
				e.getElementName(), 
				ByLocatorTypeFactory.getByType(e.getByLocatorType()),
				e.getByLocatorValue());
		grp += s;
	}
	
	@Override
	public String getValue() {		
		return grp + ";\n\n\n";
	}	

}
