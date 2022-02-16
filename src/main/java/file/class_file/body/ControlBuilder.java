/**
 * 
 */
package file.class_file.body;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import exceptions.InvalidArgumentException;
import file.annotation.NewAnnotation;
import site_mapper.creators.control_data.ControlData;
import site_mapper.creators.control_data.ControlDataFunction;
import site_mapper.creators.control_data.ControlDataFunctionFactory;
import site_mapper.creators.control_data.GroupDataInput;
import site_mapper.creators.control_data.GroupDataRow;
import site_mapper.creators.control_data.GroupDataTabs;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.ContainerFinder;
import site_mapper.jaxb.containers.Node;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Take a list of elements from ElementClass and create the 
 * 'method string' for building controls in the class body.
 *  
 */
public class ControlBuilder {
	private NewAnnotation annotation;
	private ControlDataFunctionFactory fact;
	private ElementClass clazz;	

	public ControlBuilder(ElementClass clazz) {
		this.clazz = clazz;		
		setAnnotation();
	}
	
	public ControlDataFunction buildControlFunction() {
		ControlDataFunction func = null;
		ControlDataFunctionFactory fact = new ControlDataFunctionFactory(annotation);
		ContainerMapper mapper = new ContainerMapper(fact, clazz);
		mapper.addContainers();
//		addContainers();	
		try {
			func = fact.getFunctionBuildMyControls();
		} catch (InvalidArgumentException e) {
			LogManager
				.getLogger(this.getClass())
				.error("Invalid argument in control data");
		}				
		return func;
	}
	
//	private void addContainers() {
//		List<Container> containers = clazz.getAllContainers();
//		if(containers != null) {
//			fact = new ControlDataFunctionFactory(annotation);			
//			containers.forEach(cont -> {
//				getAllForCurrentContainer(cont);				
//			});	
//		}
//	}	
//	private void getAllForCurrentContainer(Container cont) {
//		ContainerFinder finder = new ContainerFinder(new Node(null, cont));
//		Container current = finder.getNextContainer();
//		while(current != null) {
//			getControlDataForType(current.getType(), current);			
//			current = finder.getNextContainer();
//		}
//	}
//	private void getControlDataForType(String type, Container current) {
//		if(type.equalsIgnoreCase("InputGroup")) { 	
//			ControlData grp = new GroupDataInput(current);
//			fact.addGroup(grp); 	
//		}else if(type.equalsIgnoreCase("Tabs")){
//			ControlData tabs = new GroupDataTabs(current);
//			fact.addGroup(tabs); 	
//		}else if(type.equalsIgnoreCase("Row")){
//			ControlData row = new GroupDataRow(current);
//			fact.addGroup(row);
//		}
//	}	
	
	private void setAnnotation() {
		annotation = new NewAnnotation(clazz.getSiteMapInfo(), 1);
	}
	
	public NewAnnotation getAnnotation() {
		return annotation;
	}
	
}
