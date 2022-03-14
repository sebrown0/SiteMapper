/**
 * 
 */
package site_mapper.jaxb.tree;

import site_mapper.jaxb.node.Node;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface TreeVisitor {
	void addNode(Node n);
}
