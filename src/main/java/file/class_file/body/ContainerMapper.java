/**
 * 
 */
package file.class_file.body;

import java.util.List;

import site_mapper.creators.control_data.ControlDataFunctionBuilder;
import site_mapper.creators.control_data.GroupData;
import site_mapper.creators.control_data.GroupDataChild;
import site_mapper.creators.control_data.GroupDataParent;
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
	private ControlDataFunctionBuilder funcBuilder;
	private Container current;
	private GroupData grpCurrent;
	private GroupData grpParent;
	private int numChild;
	
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
		finder = new ContainerFinder(new Node(cont));				
		setCurrentContainerToNext();
		while(isValidContainer(current)) {						
			checkTypeOfContainer();			
			setCurrentContainerToNext();
		}
	}

	private void setCurrentContainerToNext() {
		current = finder.getNextContainer();		
	}
	
	private boolean isValidContainer(Container cont) {
		return (cont != null && cont.getName() != null) ? true : false;
	}
	
	private void checkTypeOfContainer() {
		if(current.isParentContiner()) {				
			setAsParentGroup();
		}else if(isStandAloneGroup()) {
			setAsStandaloneGroup();
		}else {				
			setAsGroup();
		}
	}

	private boolean isStandAloneGroup() {
		return (current.isParentContiner() || grpParent != null) ? false : true;		
	}
	
	private void setAsParentGroup() {
		grpParent = new GroupDataParent(current);				
		funcBuilder.addGroup(grpParent);
		numChild = current.getContainers().size();
	}
	private void setAsGroup() {
		grpCurrent = new GroupDataChild(current);
		grpCurrent.setElements();
		funcBuilder.addGroup(grpCurrent);
		addChildToParentIfNecessary();
	}
	private void setAsStandaloneGroup() {		
		grpCurrent = new GroupData(current);
		grpCurrent.setElements();
		funcBuilder.addGroup(grpCurrent);
	}
		
	private void addChildToParentIfNecessary() {
		if(parentGroupHasChildGroup()) {
			addChildToParent();
			if(isLastChild()) {
				resetParent();
			}				
		}			
	}
	private boolean parentGroupHasChildGroup() {
		return (grpParent != null && grpCurrent != null) ? true : false;
	}
	private void addChildToParent() {		
		grpParent.addElement(grpCurrent.getName());
		numChild--;
	}
	private boolean isLastChild() {
		return (numChild <= 0) ? true : false;
	}
	private void resetParent() {
		grpParent = null;
		numChild = 0;
	}
}
