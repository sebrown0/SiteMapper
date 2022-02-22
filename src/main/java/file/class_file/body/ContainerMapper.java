/**
 * 
 */
package file.class_file.body;

import java.util.List;

import site_mapper.creators.control_data.ControlDataFunctionBuilder;
import site_mapper.creators.control_data.GroupControlData;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.ContainerFinder;
import site_mapper.jaxb.containers.Node;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ContainerMapper {
	private ContainerFinder finder;
	private Container current;
	private GroupControlData root;
	
	public ContainerMapper(ControlDataFunctionBuilder funcBuilder) {
	
	}
	
	public void addContainers(List<Container> inContainers) {
		if(inContainers != null) {			
			inContainers.forEach(cont -> {
				getAllForCurrentContainer(cont);				
			});	
		}
	}	
	
	private void getAllForCurrentContainer(Container cont) {
		GroupControlData curr;
		GroupControlData temp = null;
		
		finder = new ContainerFinder(new Node(cont));
		setCurrentContainerToNext();		
		if(current != null) {
			root = new GroupControlData(current);
			curr = root;			
			
			root.setNext(temp);
			
			while(isValidContainer(current)) {				
				setCurrentContainerToNext();
				temp = curr;
				curr = new GroupControlData(current);
				temp.setNext(curr);
			}			
		}		
	}
	
	private void setCurrentContainerToNext() { 	
		current = finder.getNextContainer();		
	}
	
	private boolean isValidContainer(Container cont) {
		return (cont != null && cont.getName() != null) ? true : false;
	}
	
}
