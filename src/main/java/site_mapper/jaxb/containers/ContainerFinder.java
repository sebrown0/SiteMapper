/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.ArrayList;
import java.util.List;

import file.annotation.NewAnnotation;
import site_mapper.creators.control_data.ControlDataFunction;
import site_mapper.creators.control_data.ControlDataFunctionBuilder;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ContainerFinder {
	private Node[] roots;
	private Node currentNode;
	private List<Node> nodes;
	
	public ContainerFinder(Node... roots) {
		this.roots = roots;
//		this.currentNode = roots[0];
	}	

	public String getControlDataFunction() {
		ControlDataFunctionBuilder 
			builder = 
				new ControlDataFunctionBuilder(new NewAnnotation(new SiteMapInfo(), 1));
		
		if(nodes != null) {
			Node n;
			int numNodes = nodes.size()-1;
			for(int idx = 0; idx <= numNodes; idx++) {
				n = nodes.get(idx);
				builder.addNode(n);					
			}			
		}
		
		ControlDataFunction func = new ControlDataFunction(builder);
		return func.getFunctionBuildMyControls();		
	}
	public ContainerFinder traverseTree() {
		nodes = new ArrayList<>();
		
		for(int idx = 0; idx <= roots.length-1; idx++) {
			Node root = roots[idx];
			currentNode = root;
			if(isValidRoot(root)) {
				nodes.add(root);
				Container ret = setCurrentContainer();
				
				while(ret != null) {
					nodes.add(currentNode);
					ret = getNextContainer();			
				}	
			}
			
		}
				
		return this;
	}
	private boolean isValidRoot(Node root) {
		return (root.getContainers() != null || root.getElements() != null) ? true : false;
	}
//	public ContainerFinder traverseTree() {
//		nodes = new ArrayList<>();
//		
//		for(int idx = 0; idx <= roots.length; idx++) {
//			Node root = roots[idx];
//		}
//		nodes.add(roots);
//		Container ret = setCurrentContainer();
//		
//		while(ret != null) {
//			nodes.add(currentNode);
//			ret = getNextContainer();			
//		}		
//		return this;
//	}
	private Container setCurrentContainer() {
		Container ret = null;
		if(currentNode.hasAnotherContainer()) {
			ret = currentNode.getNextContainer();
			currentNode = new Node(currentNode, ret, includeXXX());
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
					currentNode = new Node(currentNode, ret, includeYYY(prev));
				}				
			}else {
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

}
