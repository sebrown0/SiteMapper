/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.ArrayList;
import java.util.List;

import file.annotation.SiteMapAnnotation;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.Node;
import site_mapper.jaxb.pom.Element;
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
public class ControlDataFunctionBuilder {
	private List<String> groupNames = new ArrayList<>();
	private List<String> elementNames = new ArrayList<>();
	private List<String> groups = new ArrayList<>();
	private List<String> elements = new ArrayList<>();
	
//	private Map<String, String> groups = new HashMap<>();
//	private Map<String, String> elements = new HashMap<>();	
	private SiteMapAnnotation anno;
	private String inMyControls = "";
	
	public ControlDataFunctionBuilder(SiteMapAnnotation anno) {		
		this.anno = anno;		
	}	

	public void addNode(Node n) {
		addNodeControls(n);
	}
	
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
				"(\"%s\", coreData, %s\n\t\t\t\t.addControls(Arrays.asList(", 
				StringUtils.camelCase(name), 
				StringUtils.pascalCase(type), 
				StringUtils.pascalCase(name), 
				locStr);
		
		if(n.isIncludedInControlList()) {
			inMyControls += n.getName() + ", ";
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
					elementNames.add(elName);
					elements.add(e.getElementString() + "\n");
				}
				incElements += e.getElementName() + ", ";
			}	
		}
//		if(nodeElements != null) {
//			for (Element e : nodeElements) {				
//				if(elements.containsKey(e.getElementName())==false) {
//					elements.put(e.getElementName(), e.getElementString() + "\n");
//				}
//				incElements += e.getElementName() + ", ";
//			}	
//		}
		
		grpStr += 
			getIncContainers(incContainers, incElements) + 
			StringUtils.removeTrailingComma(incElements) + "));\n";
		
		if(groupNames.contains(name) == false) {
			groupNames.add(name);
			groups.add(grpStr);
		}
//		if(groups.containsKey(name)==false) {
//			groups.put(name, grpStr);
//		}
		
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
//	public Map<String, String> getGroups(){
//		return groups;
//	}
//	public Map<String, String> getElements(){
//		return elements;
//	}
	public SiteMapAnnotation getAnnotation() {
		return anno;
	}
	
}
