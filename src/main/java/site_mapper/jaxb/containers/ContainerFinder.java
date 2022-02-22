/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ContainerFinder {
	private Node root;
	private Node currentNode;
	private int currLevel = 0; //Root
	private List<Node> nodes;
	
	public ContainerFinder(Node root) {
		this.root = root;
		this.currentNode = root;
	}	

	public void printNodes() {
		String 
			myControls = 
				"\t\tvar myControls =\n" +
				"\t\t\tList.of(\n";
		
		for (Node node : nodes) {
			System.out.println(getIndent(node) + "Node:"+node.getName()); // TODO - remove or log 	
			System.out.println(getIndent(node) + node.toString()); // TODO - remove or log 	
			if(node.isIncludedInControlList()) {
				myControls += String.format("\t\t\t\tnew ControlData(%s),\n", node.getName());	
			}
			
		}
		myControls += "\t\t\t);";
		
		System.out.println("-------------------------"); // TODO - remove or log
		System.out.println(myControls); // TODO - remove or log 	
	}
	private String getIndent(Node node) {
		String res = "";
			for(int i=1; i <= node.getNodeLevel(); i++) {
				res += " ";
			}
		return res;
	}
	public ContainerFinder traverseTree() {
		nodes = new ArrayList<>();
		nodes.add(root);
		Container ret = setCurrentContainer();
		
		while(ret != null) {
			nodes.add(currentNode);
			ret = getNextContainer();			
		}		
		return this;
	}
	private Container setCurrentContainer() {
		Container ret = null;
		if(currentNode.hasAnotherContainer()) {
			ret = currentNode.getNextContainer();
			//going down a level
			currLevel++;
			currentNode = new Node(currentNode, ret, currLevel, includeXXX());
		}
		return ret;
	}
	
	private boolean includeXXX() {
		return (currentNode.getPrev() == null) ? true : false;
	}
	public Container getNextContainer() {
		Node prev = currentNode.getPrev();
		Container ret = setCurrentContainer(); //new node on the same level
				
		while(ret == null && prev != null) {			
			if(prev.hasAnotherContainer()) {
				ret = prev.getNextContainer();
				if(ret != null) {
					currentNode = new Node(currentNode, ret, currLevel, includeYYY(prev));
				}				
			}else {
				currLevel--;
				prev = prev.getPrev();
			}			
		}
		return ret;
	}
	
	private boolean includeYYY(Node prev) {
		boolean ret = false;
		if(prev == null) {
			ret = true;
		}else if(prev.getPrev() == null) {//root
			ret = true;
		}
		
		return ret;
	}
//	public Container getNextContainer() {
//		Node prev = currentNode.getPrev();
//		Container ret = setCurrentContainer();
//				
//		while(ret == null && prev != null) {			
//			if(prev.hasAnotherContainer()) {
//				System.out.println("DOWN"); // TODO - remove or log
//				NextContainer next = prev.getNextContainer();
//				ret = prev.getNextContainer();
//				currentNode = new Node(currentNode, ret);
//			}else {
//				System.out.println("UP"); // TODO - remove or log
//				prev = prev.getPrev();
//			}			
//		}
//		return ret;
//	}


	
	public Container findContainer(String containerName) {
		Container ret = setCurrentContainer();
		boolean found = false;

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

	public int getCurrLevel() {
		return currLevel;
	}
	
}
