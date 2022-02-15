/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Node {
	private int currentLevel = 0;
	private List<Container> containers;
	private Container currentContainer;
	private boolean isTheNode = false;
	
	public Node(Container root) {
		this.currentContainer = root;
		this.containers = root.getContainers();
//		this.currentContainer = getCurrent();
	}

//	private Container getCurrent() {
//		if(currentLevel < containers.size()) {
//			return containers.get(currentLevel);
//		}
//		return null;
//	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}

	public List<Container> getContainers() {
		return containers;
	}

	public Container getCurrentContainer() {
		return currentContainer;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	public void setCurrentContainer(Container currentContainer) {
		this.currentContainer = currentContainer;
	}

	public boolean isTheNode() {
		return isTheNode;
	}

	public void setTheNode(boolean isTheNode) {
		this.isTheNode = isTheNode;
	}
	
	
}
