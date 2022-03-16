/**
 * 
 */
package site_mapper.jaxb.tree;

import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.node.ChildNode;
import site_mapper.jaxb.node.Node;
import site_mapper.jaxb.node.ParentNode;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TreeWalker {
	private Node currentNode;
	
	private TreeVisitor treeVisitor;
	private Node[] roots;
	
	public TreeWalker(TreeVisitor treeVisitor, Node... roots) {
		this.treeVisitor = treeVisitor;
		this.roots = roots;
	}

	public void traverseTree() {			
		for(int idx = 0; idx <= roots.length-1; idx++) {
			Node root = roots[idx];
			currentNode = root;
			if(isValidRoot(root)) {
				Container ret = setCurrentContainer();								
				while(ret != null) {
					addNodeToVisitor(currentNode); 
					ret = getNextContainer();									
				}	
			}			
		}				
	}
	
	private void addNodeToVisitor(Node n) {
		if(n != null) {			
			treeVisitor.addNode(currentNode);				 	
		}
	}
		
	private boolean isValidRoot(Node root) {
		return (root.getContainers() != null || root.getElements() != null) ? true : false;
	}
	
	private Container setCurrentContainer() {
		Container ret = currentNode.getCurrentContainer();
		if(currentNode != null && currentNode.hasAnotherContainer()) {			
			ret = currentNode.getNextContainer();
			if(ret.isParentContiner()) {
				currentNode = new ParentNode(ret);
			}else {
				currentNode = new ChildNode(currentNode, ret).includeInControlList();	
			}							
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
						if(ret.isParentContiner()) {
							currentNode = new ParentNode(ret);
						}else {
							currentNode = new ChildNode(currentNode, ret).includeInControlList();	
						}
					}
				}else {
					prev = prev.getPrev();
				}			
			}	
		}		
		return ret;
	}

}
