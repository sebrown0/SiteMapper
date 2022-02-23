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
public class TreeWalker implements TreeNodes {
	private List<Node> nodes;
	private Node currentNode;
	private TreeVisitor treeVisitor;
	private Node[] roots;
	
	public TreeWalker(TreeVisitor treeVisitor, Node... roots) {
		this.treeVisitor = treeVisitor;
		this.roots = roots;
		this.nodes = new ArrayList<>();
	}

	public TreeWalker traverseTree() {			
		for(int idx = 0; idx <= roots.length-1; idx++) {
			Node root = roots[idx];
			currentNode = root;
			if(isValidRoot(root)) {
				nodes.add(root);
				Container ret = setCurrentContainer();				
				while(ret != null) {
					nodes.add(currentNode);
					ret = getNextContainer();			
					treeVisitor.addNode(currentNode);					
				}	
			}			
		}				
		return this;
	}
	
	private boolean isValidRoot(Node root) {
		return (root.getContainers() != null || root.getElements() != null) ? true : false;
	}
	
	private Container setCurrentContainer() {
		Container ret = null;
		if(currentNode != null && currentNode.hasAnotherContainer()) {
			ret = currentNode.getNextContainer();
			currentNode = new Node(currentNode, ret, isContainerIncludedInControlList());
		}
		return ret;
	}
	
	private boolean isContainerIncludedInControlList() {
		return (currentNode.getPrev() == null) ? true : false;
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
						currentNode = new Node(currentNode, ret, isNodeIncludedInControlList(prev));
					}				
				}else {
					prev = prev.getPrev();
				}			
			}	
		}		
		return ret;
	}
	
	private boolean isNodeIncludedInControlList(Node prev) {
		boolean ret = false;
		if(prev == null) {
			ret = true;
		}else if(prev.getPrev() == null) {//root
			ret = true;
		}		
		return ret;
	}

	@Override //TreeNodes
	public List<Node> getNodes() {
		return nodes;
	}
}
