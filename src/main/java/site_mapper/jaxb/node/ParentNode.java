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
 * Parent nodes are added to myControls 
 * and have no previous node.
 */
public class ParentNode extends Node {

	public ParentNode(Container container) {
		super(container);
				
		super.isIncludedInControlList = true;
	}

}
