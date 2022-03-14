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
public class TreeWalker {
	private List<Node> nodes;
	private Node currentNode;
	private TreeVisitor treeVisitor;
	private Node[] roots;
	
	public TreeWalker(TreeVisitor treeVisitor, Node... roots) {
		this.treeVisitor = treeVisitor;
		this.roots = roots;
		this.nodes = new ArrayList<>();
	}

	public void traverseTree() {			
		for(int idx = 0; idx <= roots.length-1; idx++) {
			Node root = roots[idx];
			currentNode = root;
			if(isValidRoot(root)) {
				Container ret = setCurrentContainer();
				nodes.add(root);
				treeVisitor.addNode(root);								
				while(ret != null) {
					nodes.add(currentNode);
					treeVisitor.addNode(currentNode);				  
					ret = getNextContainer();									
				}	
			}			
		}				
	}
	
	private boolean isValidRoot(Node root) {
		return (root.getContainers() != null || root.getElements() != null) ? true : false;
	}
	
	private Container setCurrentContainer() {
		Container ret = currentNode.getCurrentContainer();
		if(currentNode != null && currentNode.hasAnotherContainer()) {			
			ret = currentNode.getNextContainer();
			currentNode = new Node(currentNode, ret);				
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
						currentNode = new Node(currentNode, ret);
					}				
				}else {
					prev = prev.getPrev();
				}			
			}	
		}		
		return ret;
	}

}
