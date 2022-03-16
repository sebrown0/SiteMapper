/**
 * 
 */
package site_mapper.jaxb.node;

import site_mapper.jaxb.containers.Container;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Child nodes are not added to myControls 
 * and have a previous node.
 * 
 */
public class ChildNode extends Node {

	public ChildNode(Node prev, Container container) {
		super(container);

		super.isIncludedInControlList = false;
		super.prev = prev;
	}

	public ChildNode includeInControlList() {
		super.isIncludedInControlList = true;
		return this;
	}
}
