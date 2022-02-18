/**
 * 
 */
package file.class_file.body;

import java.util.List;

import site_mapper.creators.control_data.ControlDataFunctionBuilder;
import site_mapper.creators.control_data.GroupData;
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
	private ControlDataFunctionBuilder funcBuilder;
	
	public ContainerMapper(ControlDataFunctionBuilder funcBuilder) {
		this.funcBuilder = funcBuilder;	
	}
	
	public void addContainers(List<Container> inContainers) {
		if(inContainers != null) {			
			inContainers.forEach(cont -> {
				getAllForCurrentContainer(cont);				
			});	
		}
	}	
	
	private void getAllForCurrentContainer(Container cont) {
		ContainerFinder finder = new ContainerFinder(new Node(cont));
		Container current = finder.getNextContainer();
		GroupData grpCurrent = null;
		GroupData grpParent = null;
		int numChild = 0;
		
		while(isValidContainer(current)) {
						
			if(current.getContainers() != null) {				
				grpParent = new GroupData(current);				
				funcBuilder.addGroup(grpParent);
				numChild = current.getContainers().size();
			}else {				
				grpCurrent = new GroupData(current);
				grpCurrent.setElements();
				funcBuilder.addGroup(grpCurrent);			
			}
			
			if(grpParent != null && grpCurrent != null) {
				System.out.println(grpParent.getName() + " is parent"); // TODO - remove or log
				System.out.println("  "  + grpCurrent.getName()); // TODO - remove or log
				grpParent.addElement(grpCurrent.getName());
				numChild--;
				if(numChild <= 0) {
					grpParent = null;
					numChild = 0;
				}				
			}
			
			
//			funcBuilder.addGroup(grpCurrent);				
			current = finder.getNextContainer();
		}
	}
	
//	private void getAllForCurrentContainer(Container cont) {
//		ContainerFinder finder = new ContainerFinder(new Node(cont));
//		Container current = finder.getNextContainer();
//		GroupData grpCurrent, grpLast = null;
//		
//		while(isValidContainer(current)) {
//			
//			grpCurrent = new GroupData(current);
//			funcBuilder.addGroup(grpCurrent);				
//			current = finder.getNextContainer();
//			
//			if(grpLast != null) {
//				grpLast.addElements(grpCurrent.getAddToArrays());
//			}
//			
//			
//			grpLast = grpCurrent;
//		}
//	}
	
	private boolean isValidContainer(Container cont) {
		return (cont != null && cont.getName() != null) ? true : false;
	}

}
