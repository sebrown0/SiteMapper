/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.ArrayList;
import java.util.List;

import file.annotation.SiteMapAnnotation;
import file.imports.ControlImportGetter;
import site_mapper.creators.ComponentWriter;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.Node;
import site_mapper.jaxb.containers.TreeVisitor;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.SiteMapInfo;
import utils.StringUtils;

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
	
	public ControlDataFunctionBuilder(SiteMapAnnotation anno, ComponentWriter cw, SiteMapInfo info) {		
		this.anno = anno;		
		this.compWriter = cw;
		this.info = info;
	}	

	@Override //TreeVisitor
	public void addNode(Node n) {
		if(isValidNode(n))
			addNodeControls(n);
	}
	
	private boolean isValidNode(Node n) {
		return (
				n != null && 
				n.getName() != null && n.getName().length() > 0 && 
				n.getType() != null && n.getType().length() > 0 ) ? true : false;
	}
		
	private String getLocator(String loc) {
		return (loc != null && loc.length() > 0) ? ", " + loc : "";
	}
	
	//TODO - refactor
	private void addNodeControls(Node n) {
		String name = n.getName();
		String type = n.getType();
		String locStr = n.getLocStr();
		String incElements = "";
		String incContainers = "";		
		List<Container> nodeContainers = n.getContainers();
		List<Element> nodeElements = n.getElements();
				
		String grpStr = String.format(
				"\t\tControlGetterGroup %s =\n\t\t\tnew ControlGetter%s" +
				"(\"%s\", coreData%s)\n\t\t\t\t.addControls(Arrays.asList(", 
				StringUtils.camelCase(name), 
				StringUtils.pascalCase(type), 
				StringUtils.pascalCase(name), 
				getLocator(locStr));
		
		if(n.isIncludedInControlList()) {
			var cntrlName = StringUtils.camelCase(name);
			if(inMyControls.contains(cntrlName) == false) {
				inMyControls += 
						String.format(
								"\n\t\t\t\tnew ControlData(%s),", 
								cntrlName);	
			}			
		}
		
		if(nodeContainers != null) {
			for (Container c : nodeContainers) {				
				incContainers += c.getName() + ", ";						
			}	
		}
		if(nodeElements != null) {
			String elName;
			for (Element e : nodeElements) {	
				elName = e.getElementName();
				if(elementNames.contains(elName) == false) {
					addToImports(e.getElementType());
					elementNames.add(elName);
					elements.add(e.getElementString() + "\n");
				}
				incElements += e.getElementName() + ", ";
			}	
		}
		
		grpStr += 
			getIncContainers(incContainers, incElements) + 
			StringUtils.removeTrailingComma(incElements) + "));\n";
		
		if(groupNames.contains(name) == false) {
			addToImports(type);
			groupNames.add(name);
			groups.add(grpStr);
		}		
	}
	
	private void addToImports(String type) {
		compWriter
			.addImport(
				ControlImportGetter
				.getImportForContolGetter(type, info));
	}
		
	private String getIncContainers(String incContainers, String incElements) {
		return 
			(incElements.length()>0) ? 
				incContainers : StringUtils.removeTrailingComma(incContainers);
	}
	public String getInMyControls() {
		return StringUtils.removeTrailingComma(inMyControls);
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
