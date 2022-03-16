/**
 * 
 */
package site_mapper.jaxb.tree;

import java.util.ArrayList;
import java.util.List;

import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.node.ChildNode;
import site_mapper.jaxb.node.Node;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ContainerWalker {
	private List<Node> nodes;
	private Node currentNode;
	private Node root;
	private List<Container> containers = new ArrayList<>();
		
	public ContainerWalker(Node root) {
		this.root = root;
		this.nodes = new ArrayList<>();
	}

	public List<Container> traverseContainers() {
		currentNode = root;
		if(isValidRoot(root)) {
			Container ret = setCurrentContainer();
			addCont(ret);
			nodes.add(root); 									
			while(ret != null) {
				nodes.add(currentNode);
				ret = getNextContainer();
				addCont(ret);					
			}	
		}
		return containers;									
	}
	
	private void addCont(Container c) {
		if(c != null) {			
			containers.add(c);	
		}
	}
		
	private boolean isValidRoot(Node root) {
		return (root.getContainers() != null || root.getElements() != null) ? true : false;
	}
	
	private Container setCurrentContainer() {
		Container ret = currentNode.getCurrentContainer();
		if(currentNode != null && currentNode.hasAnotherContainer()) {			
			ret = currentNode.getNextContainer();
			currentNode = new ChildNode(currentNode, ret);				
		}		
		return ret;
	}

	public Container getNextContainer() {
		Container ret = null;
		
		if(currentNode != null) {
			Node prev = currentNode.getPrev();
			ret = setCurrentContainer(); //new node on the same level
					
			while(ret == null && prev != null) {			
				if(prev.hasAnotherContainer()) {
					ret = prev.getNextContainer();
					if(ret != null) {
						currentNode = new ChildNode(currentNode, ret);
					}				
				}else {
					prev = prev.getPrev();
				}			
			}	
		}		
		return ret;
	}

}
