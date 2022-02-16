/**
 * 
 */
package file.class_file.body;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import exceptions.InvalidArgumentException;
import file.annotation.NewAnnotation;
import site_mapper.creators.control_data.ControlData;
import site_mapper.creators.control_data.ControlDataFunction;
import site_mapper.creators.control_data.ControlDataFunctionFactory;
import site_mapper.creators.control_data.ControlDataValues;
import site_mapper.creators.control_data.InputGroupData;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.ContainerFinder;
import site_mapper.jaxb.containers.Node;
import site_mapper.jaxb.pom.Element;

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
	
	List<ControlDataValues> vals = new ArrayList<>();
	
	public ControlBuilder(ElementClass clazz) {
		this.clazz = clazz;		
		setAnnotation();
	}
	
	public ControlDataFunction buildControlFunction() {
		ControlDataFunction func = null;				
		List<Container> containers = clazz.getAllContainers();
		
		if(containers != null) {
			containers.forEach(cont -> {
				final Node PREV = null;
				Node rootNode = new Node(PREV, cont);
				ContainerFinder finder = new ContainerFinder(rootNode);
				Container current = finder.getNextContainer();
				while(current != null) {
					System.out.println(current.getName() + " - " + current.getType()); // TODO - remove or log
					String type = current.getType();
					if(type.equalsIgnoreCase("InputGroup")) {
						System.out.println("Create input group"); // TODO - remove or log 	
						ControlData grp = new InputGroupData(current);
						System.out.println("\n" + grp.getValue()); // TODO - remove or log 	
					}else if(type.equalsIgnoreCase("Row")){
						System.out.println("Create row"); // TODO - remove or log
					}
					
					List<Element> elements = current.getElements();
					elements.forEach(e -> { 
						System.out.println("  " + e.getElementName());
						vals.add(new ControlDataValues(e));
					});
					current = finder.getNextContainer();
				}
			});	
		}
		
		if(vals != null) {							
			fact = new ControlDataFunctionFactory(vals, annotation);
			try {
				func = fact.getFunctionBuildMyControls();
			} catch (InvalidArgumentException e1) {
				LogManager
					.getLogger(this.getClass())
						.error("Error creating control data function [" + e1 + "]");
			} 
		}
		/*
		 * here we have to get the containers
		 */
//		if(containers != null) {
//			vals = 
//					containers.stream()
//						.map(e -> (ElementCreation)e)
//							.map(e -> new ControlDataValues(e))
//							.collect(Collectors.toList());
//							
//			fact = new ControlDataFunctionFactory(vals, annotation);
//			try {
//				func = fact.getFunctionBuildMyControls();
//			} catch (InvalidArgumentException e1) {
//				LogManager
//					.getLogger(this.getClass())
//						.error("Error creating control data function [" + e1 + "]");
//			} 
//		}
			
		return func;
	}

	private void setAnnotation() {
		annotation = new NewAnnotation(clazz.getSiteMapInfo(), 1);
	}
	
	public NewAnnotation getAnnotation() {
		return annotation;
	}
	
}
