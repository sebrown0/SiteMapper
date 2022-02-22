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
//	private ControlDataFunctionBuilder funcBuilder;
	private Container current;
//	private GroupData grpCurrent;
//	private GroupData grpParent;
//	private int numChild;
	
	public ContainerMapper(ControlDataFunctionBuilder funcBuilder) {
//		this.funcBuilder = funcBuilder;	
	}
	
	public void addContainers(List<Container> inContainers) {
		if(inContainers != null) {			
			inContainers.forEach(cont -> {
				getAllForCurrentContainer(cont);				
			});	
		}
	}	
	
	private void getAllForCurrentContainer(Container cont) {
//		String tab = "";
		
		finder = new ContainerFinder(new Node(cont));				
//		System.out.println("Curr Level->" + finder.getCurrLevel()); // TODO - remove or log 	
		setCurrentContainerToNext();
		
		if(current != null) {
			GroupControlData root = new GroupControlData(current);
			GroupControlData curr = root;			
			GroupControlData temp = null;
			root.setNext(temp);
			
			while(isValidContainer(current)) {
				
//				checkTypeOfContainer();		
				setCurrentContainerToNext();
				temp = curr;
				curr = new GroupControlData(current);
				temp.setNext(curr);

			}
			
//			if(root != null) {
//				temp = root;
//				while(temp != null) {
//					
//					System.out.println(tab + temp.getName()); // TODO - remove or log
//					List<Element> els = temp.getElements(); 
//					if(els != null) {
//						for (Element e : els) {
//							System.out.println(tab + " -" + e.getElementName());	
//						}
//					}
//					tab += "  ";
//					temp = temp.getNext();
//				}	
//			}	
		}
		
	}
	
	private void setCurrentContainerToNext() {
//		System.out.println("-----------------"); // TODO - remove or log 	
//		System.out.println("Curr level ->" + finder.getCurrLevel()); // TODO - remove or log 	
		current = finder.getNextContainer();		
//		System.out.println("Curr level ->" + finder.getCurrLevel()); // TODO - remove or log
	}
	
	private boolean isValidContainer(Container cont) {
		return (cont != null && cont.getName() != null) ? true : false;
	}
	
//	private void checkTypeOfContainer() {
//		if(current.isParentContiner()) {				
//			setAsParentGroup();
//		}else if(isStandAloneGroup()) {
//			setAsStandaloneGroup();
//		}else {				
//			setAsGroup();
//		}
//	}

//	private boolean isStandAloneGroup() {
//		return (current.isParentContiner() || grpParent != null) ? false : true;		
//	}
//	
//	private void setAsParentGroup() {
//		grpParent = new GroupDataParent(current);				
//		funcBuilder.addGroup(grpParent);
//		numChild = current.getContainers().size();
//	}
//	private void setAsGroup() {
//		grpCurrent = new GroupDataChild(current);
//		grpCurrent.setElements();
//		funcBuilder.addGroup(grpCurrent);
//		addChildToParentIfNecessary();
//	}
//	private void setAsStandaloneGroup() {		
//		
////		if(grpCurrent != null) {
////			System.out.println("->" + grpCurrent.getName()); // TODO - remove or log
//		System.out.println(current.getName()); // TODO - remove or log 	
//		List<Element> els = current.getElements();
//		if(els != null) {
//			els.forEach(e -> System.out.println(" " + e.getElementString())); 	
//		}
//		
//		grpCurrent = new GroupData(current);
//		grpCurrent.setElements();
//		funcBuilder.addGroup(grpCurrent);
//	}
		
//	private void addChildToParentIfNecessary() {
//		if(parentGroupHasChildGroup()) {
//			addChildToParent();
//			if(isLastChild()) {
//				resetParent();
//			}				
//		}			
//	}
//	private boolean parentGroupHasChildGroup() {
//		return (grpParent != null && grpCurrent != null) ? true : false;
//	}
//	private void addChildToParent() {		
//		grpParent.addChild(grpCurrent.getName());
//		numChild--;
//	}
//	private boolean isLastChild() {
//		return (numChild <= 0) ? true : false;
//	}
//	private void resetParent() {
//		grpParent = null;
//		numChild = 0;
//	}
}
