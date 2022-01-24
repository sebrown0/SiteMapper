/**
 * 
 */
package file.class_file.body;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;

import exceptions.InvalidArgumentException;
import file.annotation.NewAnnotation;
import site_mapper.creators.ControlDataFunction;
import site_mapper.creators.ControlDataFunctionFactory;
import site_mapper.creators.ControlDataValues;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementCreation;

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
	private NewAnnotation annotation;
	private ControlDataFunctionFactory fact;
	private ElementClass clazz;
	
	public ControlBuilder(ElementClass clazz) {
		this.clazz = clazz;		
		setAnnotation();
	}
	
	public ControlDataFunction buildControlFunction() {
		ControlDataFunction func = null;		
		
		List<ControlDataValues> vals = 
				clazz.getElements().stream()
					.map(e -> (ElementCreation)e)
						.map(e -> new ControlDataValues(e))
						.collect(Collectors.toList());
						
		fact = new ControlDataFunctionFactory(vals, annotation);
		try {
			func = fact.getFunctionBuildMyControls();
		} catch (InvalidArgumentException e1) {
			LogManager
				.getLogger(this.getClass())
					.error("Error creating control data function [" + e1 + "]");
		} 	
		return func;
	}

	private void setAnnotation() {
		annotation = new NewAnnotation(clazz.getSiteMapInfo(), 1);
	}
	
	public NewAnnotation getAnnotation() {
		return annotation;
	}
	
}
