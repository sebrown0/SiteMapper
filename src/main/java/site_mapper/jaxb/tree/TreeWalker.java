/**
 * 
 */
package site_mapper.jaxb.tree;

import java.util.ArrayList;
import java.util.List;

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
				addNodeToVisitor(root);
//				treeVisitor.addNode(root);								
				while(ret != null) {
					nodes.add(currentNode);
					addNodeToVisitor(currentNode);
//					treeVisitor.addNode(currentNode);				  
					ret = getNextContainer();									
				}	
			}			
		}				
	}
	
	private void addNodeToVisitor(Node n) {
		if(n != null) {			
//		if(n != null && notExcluded(n)) {			
			treeVisitor.addNode(currentNode);				 	
		}
	}
	
//	private boolean notExcluded(Node n) {
//		String name = n.getName();
//		return (
//				name.startsWith("Header") || 
//				name.startsWith("Body") || 
//				name.startsWith("Footer")) ? false : true;
//	}
	
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
//					if(ret != null) {
//						currentNode = new ChildNode(currentNode, ret);
//					}				
				}else {
					prev = prev.getPrev();
				}			
			}	
		}		
		return ret;
	}

}
