/**
 * 
 */
package site_mapper.jaxb.containers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.annotation.NewAnnotation;
import site_mapper.creators.component_writer.ComponentWriter;
import site_mapper.creators.control_data.ControlDataFunction;
import site_mapper.creators.control_data.ControlDataFunctionBuilder;
import site_mapper.creators.imports.FoundImports;
import site_mapper.jaxb.node.Node;
import site_mapper.jaxb.pom.SiteMapInfo;
import site_mapper.jaxb.tree.TreeWalker;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the buildMyControls() function as 
 * a string from the containers in the XML file.
 * 
 * This is required by all POM classes that 
 * have controls. 
 */
public class ControlStringFromContainers {
	private Node[] roots;
	private Logger logger;
	private ControlDataFunctionBuilder treeVisitor;
	
	public ControlStringFromContainers(
		SiteMapInfo info, FoundImports foundImports,	ComponentWriter componentWriter, Node... roots) {
		
		this.roots = roots;
			
		logger = LogManager.getLogger(ControlStringFromContainers.class);		
		treeVisitor = 
			new ControlDataFunctionBuilder(
				new NewAnnotation(info), componentWriter, info, foundImports);	
	}	
		
	public String getBuildMyControlsString() {
		walkTreeAndAddNodesToFunction();		
		return getMyControlsString();
	}
		
	private void walkTreeAndAddNodesToFunction() {
		logger.info("Walking tree to find nodes");
		TreeWalker treeWalker = new TreeWalker(treeVisitor, roots);
		treeWalker.traverseTree();
	}
	
	private String getMyControlsString() {
		logger.info("Getting myControl string");
		ControlDataFunction func = new ControlDataFunction(treeVisitor);		
		return func.getFunctionBuildMyControls();
	}
}
