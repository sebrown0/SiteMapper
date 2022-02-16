/**
 * 
 */
package site_mapper.jaxb.containers;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ContainerFinder {	
	private Node currentNode;
	
	public ContainerFinder(Node root) {		
		this.currentNode = root;
	}
	
	public Container getNextContainer() {
		Node prev = currentNode.getPrev();
		Container ret = setCurrentContainer();
				
		while(ret == null && prev != null) {			
			if(prev.hasAnotherContainer()) {
				ret = prev.getNextContainer();
				currentNode = new Node(currentNode, ret);
			}else {
				prev = prev.getPrev();
			}			
		}
		return ret;
	}

	private Container setCurrentContainer() {
		Container ret = null;
		if(currentNode.hasAnotherContainer()) {
			ret = currentNode.getNextContainer();
			currentNode = new Node(currentNode, ret);
		}
		return ret;
	}
	
	public Container findContainer(String containerName) {
		Container ret = setCurrentContainer();
		boolean found = false;
//		Container c = currentNode.getCurrentContainer();
		
		while(found == false && ret != null) {			
			var name = ret.getName();
			if(name.equals(containerName)) {				
				found = true;
			}else {
				ret = getNextContainer();
			}
			
		}
		return ret;
	}
	
}
