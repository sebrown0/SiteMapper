/**
 * 
 */
package file.class_file.body;

import org.apache.logging.log4j.LogManager;

import exceptions.InvalidArgumentException;
import file.annotation.NewAnnotation;
import site_mapper.creators.control_data.ControlDataFunction;
import site_mapper.creators.control_data.ControlDataFunctionBuilder;
import site_mapper.elements.ElementClass;

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
	private ElementClass clazz;	
	private ControlDataFunction func = null;
	private ControlDataFunctionBuilder funcBuilder;
	
	public ControlBuilder(ElementClass clazz) {
		this.clazz = clazz;		
	}
	
	public ControlDataFunction buildControlFunction() {
		setFunctionBuilder();
		mapContainers();
		try {
			func = funcBuilder.getFunctionBuildMyControls();
		} catch (InvalidArgumentException e) {
			LogManager
				.getLogger(this.getClass())
				.error("Invalid argument in control data");
		}				
		return func;
	}
	
	private void setFunctionBuilder() {
		funcBuilder = 
				new ControlDataFunctionBuilder(
						new NewAnnotation(clazz.getSiteMapInfo(), 1));
	}
	
	private void mapContainers() {		
		ContainerMapper mapper = new ContainerMapper(funcBuilder);
		mapper.addContainers(clazz.getAllContainers());
	}
		
}
