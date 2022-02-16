/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.Optional;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ContainerFinder {
	private Node root;
	private Node current;
	
	public ContainerFinder(Node root) {
		this.root = root;
		this.current = root;
	}
	
	public Container getNextContainer() {
		Container ret = null;
		
		if(current.hasAnotherContainer()) {
			ret = current.getNextContainer();
			current = new Node(current, ret);
		}
		
		Node prev = current.getPrev();
		while(ret == null && prev != null) {			
			if(prev.hasAnotherContainer()) {
				ret = prev.getNextContainer();
				current = new Node(current, ret);
			}else {
				prev = prev.getPrev();
			}			
		}
		return ret;
	}
//	public Container getNextContainer() {
//		Container ret = null;
//		
//		if(current.hasAnotherContainer()) {
//			ret = current.getNextContainer();
//			current = new Node(current, ret);
//		}
//		
//		Node prev = current.getPrev();
//		while(ret == null && prev != null) {			
//			if(prev == null) {
//				System.out.println("AT ROOT"); // TODO - remove or log 	
//				break;
//			}else {
//				if(prev.hasAnotherContainer()) {
//					ret = prev.getNextContainer();
//					current = new Node(current, ret);
//				}else {
//					prev = prev.getPrev();
//				}
//			}
//		}
//		return ret;
//	}
	
}
