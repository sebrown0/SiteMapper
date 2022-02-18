/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.helpers.LocatorFactory;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.Locator;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class GroupData implements ControlData {
	private String name;
	private String varName;
	private String locator;
	private String grp;	
	private Container cont;	
	private Logger logger = LogManager.getLogger(GroupData.class);
	
	private List<String> addToArrays = new ArrayList<>();
	
	private boolean notFirstElement = false;
	private int elementInLineCount = 0;
	
	public GroupData(Container cont) {
		this.cont = cont;
		setup();
	}
	public GroupData(Container cont, GroupData grp) {
		this.cont = cont;
		setup();
	}
	
	private void setup() {
		if(cont != null) {			
			setName();
			setVarName();
			setLocator();
			setInitialData();
//			setElements();
		}		
	}
	private void setName() {
		name = StringUtils.pascalCase(cont.getName());		
	}
	private void setVarName() {
		varName = StringUtils.camelCase(cont.getName());
	}
	private void setLocator() {
		Locator loc = cont.getloLocator();
		if(loc != null) {
			locator = LocatorFactory.getByString(loc);
		}else {
			logger.info("No locator found for group");
		}
	}
	private void setInitialData() {
		grp = String.format(
				"\t\tControlGetter %s = " +
				"\n\t\t\tnew ControlGetter%s(%s, coreData %s))" +
				"\n\t\t\t\t.addControls(Arrays.asList(", 
				varName, cont.getType(), name, getLocator());				
	}

	private String getLocator() {
		return (locator != null) ? ", " + locator : "";
	}
	
//	private void setElements() {
//		List<Element> elements = cont.getElements();
//		if(elements != null) {
//			elements.forEach(e -> {
//				addToArrays.add(e.getElementName());
////				addElement(e);
//			});	
//		}		
//	}
	
	public void setElements() {
		List<Element> elements = cont.getElements();
		if(elements != null) {
			elements.forEach(e -> {				
				addElement(e);
			});	
		}		
	}
	
	public void addElements(List<String> elements) {
		if(elements != null) {
			elements.forEach(e -> {
				addElement(e);
			});	
		}
	}
	public void addElement(String elName) {
		String s;
		
		addToArrays.add(elName);
		
		if(elementInLineCount > 2) {
			s = String.format("\n\t\t\t\t\t%s",	elName);	
			elementInLineCount = 0;
		}else {
			s = String.format("%s", elName);
			elementInLineCount++;
		}		
		
		if(notFirstElement) {
			grp += ", " + s;	
		}else {
			grp += "\n\t\t\t\t\t" + s;
			notFirstElement = true;
		}		
	}

	
	private void addElement(Element e) {
		String s, elName = e.getElementName();
		
		addToArrays.add(elName);
		
		if(elementInLineCount > 2) {
			s = String.format("\n\t\t\t\t\t%s",	elName);	
			elementInLineCount = 0;
		}else {
			s = String.format("%s", elName);
			elementInLineCount++;
		}		
		
		if(notFirstElement) {
			grp += ", " + s;	
		}else {
			grp += "\n\t\t\t\t\t" + s;
			notFirstElement = true;
		}		
	}
	
	@Override
	public String getValue() {		
		return grp + "));";
	}
	@Override
	public String getControlDataValue() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<String> getAddToArrays() {
		return addToArrays;
	}
	public String getName() {
		return name;
	}	

}
