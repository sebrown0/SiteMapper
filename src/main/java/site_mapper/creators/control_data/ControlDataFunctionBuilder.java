/**
 * 
 */
package site_mapper.creators.control_data;

import static utils.StringUtils.asCamelCase;
import static utils.StringUtils.asPascalCase;
import static utils.StringUtils.removeTrailingComma;

import java.util.ArrayList;
import java.util.List;

import file.annotation.SiteMapAnnotation;
import file.imports.ControlImportGetter;
import site_mapper.creators.component_writer.ComponentWriter;
import site_mapper.creators.imports.FoundImports;
import site_mapper.elements.ElementWithAcronym;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.node.Node;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.SiteMapInfo;
import site_mapper.jaxb.tree.TreeVisitor;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * TODO TODO TODO TODO
 * Get a string that can be added to a 
 * class file as the java code for creating
 * a new ControlData object, i.e.
 * 	"new ControlData(
 * 		"control name", 
 * 		new ControlGetterTextOut(coreData, By.id("FORM_ID")))"
 * 
 */
public class ControlDataFunctionBuilder implements TreeVisitor {
	private List<String> groupNames = new ArrayList<>();
	private List<String> elementNames = new ArrayList<>();
	private List<String> groups = new ArrayList<>();
	private List<String> elements = new ArrayList<>();	
	private SiteMapAnnotation anno;
	private String inMyControls = "";
	private ComponentWriter compWriter;
	private SiteMapInfo info;
	private FoundImports foundImports;
	
	public ControlDataFunctionBuilder(
		SiteMapAnnotation anno, ComponentWriter cw, 
		SiteMapInfo info, FoundImports foundImports) {
		
		this.anno = anno;		
		this.compWriter = cw;
		this.info = info;
		this.foundImports = foundImports;
	}	

	@Override //TreeVisitor
	public void addNode(Node n) {
		if(isValidNode(n)) addNodeControls(n);
	}
	
	private boolean isValidNode(Node n) {
		return (
				n != null && 
				n.getName() != null && n.getName().length() > 0 && 
				n.getType() != null && n.getType().length() > 0 && 
				notExcluded(n)) ? true : false;
	}
		
	private boolean notExcluded(Node n) {
		String name = n.getName();
		boolean res = (
				name.startsWith("Header") || 
				name.startsWith("Body") || 
				name.startsWith("Footer")) ? false : true; 
		
		return res;
	}
	
	private String getLocator(String loc) {
		return (loc != null && loc.length() > 0) ? ", " + loc : "";
	}
	
	//TODO - refactor
	private void addNodeControls(Node n) {
		String nodeName = n.getName();
		String nodeNamePascal = asPascalCase(nodeName);
		String nodeNameCamel = asCamelCase(nodeName);

		String type = n.getType();
		String locStr = n.getLocStr();
		String incElements = "";
		String incContainers = "";		
		
		String nameWithAcronym = null;
		
		List<Container> nodeContainers = n.getContainers();
		List<Element> nodeElements = n.getElements();
				
		String grpStr = String.format(
				"\t\tControlGetterGroup %s =\n\t\t\tnew ControlGetter%s" +
				"(\"%s\", coreData%s)\n\t\t\t\t.addControls(Arrays.asList(", 
				nodeNameCamel, 
				asPascalCase(type), 
				nodeNamePascal, 
				getLocator(locStr));
		
		if(n.isIncludedInControlList()) {
			if(inMyControls.contains(nodeNameCamel) == false) {
				inMyControls += 
						String.format(
								"\n\t\t\t\tnew ControlData(%s),", 
								nodeNameCamel);
			}			
		}
		
		if(nodeContainers != null) {
			for (Container c : nodeContainers) {				
				incContainers += asCamelCase(c.getName()) + ", ";						
			}	
		}
		
		if(nodeElements != null) {
			for (Element e : nodeElements) {	
				nameWithAcronym = getNameWithAcronym(e);
				if(elementNames.contains(nameWithAcronym) == false) {
					addToImports(e.getElementType());					
					elementNames.add(nameWithAcronym);
					elements.add(e.getElementAsControlGetter() + "\n");
					incElements += nameWithAcronym + ", ";
				}
			}	
		}
		
		grpStr += 
			getIncContainers(incContainers, incElements) + 
			removeTrailingComma(incElements) + "));\n";
		
		if(groupNames.contains(nodeName) == false) {
			addToImports(type);
			groupNames.add(nodeName);
			groups.add(grpStr);
		}		
	}
	
	private String getNameWithAcronym(ElementWithAcronym e) {
		return e.getNameWithAcronym();
	}
	
	private void addToImports(String type) {
		compWriter
			.addImport(
				ControlImportGetter
				.getImportForContolGetter(type, info, foundImports));
	}
		
	private String getIncContainers(String incContainers, String incElements) {
		return 
			(incElements.length()>0) ? 
				incContainers : removeTrailingComma(incContainers);
	}
	public String getInMyControls() {
		return removeTrailingComma(inMyControls);
	}
	public List<String> getGroups(){
		return groups;
	}
	public List<String> getElements(){
		return elements;
	}
	public SiteMapAnnotation getAnnotation() {
		return anno;
	}
	
}
