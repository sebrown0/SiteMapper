/**
 * 
 */
package file.class_file.body;

import java.util.List;

import site_mapper.creators.control_data.ControlData;
import site_mapper.creators.control_data.ControlDataFunctionBuilder;
import site_mapper.creators.control_data.GroupDataInput;
import site_mapper.creators.control_data.GroupDataRow;
import site_mapper.creators.control_data.GroupDataTabs;
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
		ContainerFinder finder = new ContainerFinder(new Node(null, cont));
		Container current = finder.getNextContainer();
		while(current != null) {
			getControlDataForType(current.getType(), current);			
			current = finder.getNextContainer();
		}
	}
	private void getControlDataForType(String type, Container current) {
		if(type.equalsIgnoreCase("InputGroup")) { 	
			ControlData grp = new GroupDataInput(current);
			funcBuilder.addGroup(grp); 	
		}else if(type.equalsIgnoreCase("Tabs")){
			ControlData tabs = new GroupDataTabs(current);
			funcBuilder.addGroup(tabs); 	
		}else if(type.equalsIgnoreCase("Row")){
			ControlData row = new GroupDataRow(current);
			funcBuilder.addGroup(row);
		}
	}	
}
