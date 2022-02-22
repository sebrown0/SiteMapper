/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.InvalidArgumentException;
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
 * Get a string that can be added to a 
 * class file as the java code for creating
 * a new ControlData object, i.e.
 * 	"new ControlData(
 * 		"control name", 
 * 		new ControlGetterTextOut(coreData, By.id("FORM_ID")))"
 * 
 */
public class ControlDataFunctionBuilder {
	private Map<String, String> groups = new HashMap<>();
	private Map<String, String> elements = new HashMap<>();
	
	private List<ControlData> zzz_groups;
	private List<ControlData> zzz_values;
	private String func = "";

	private int numControls;
	private SiteMapAnnotation anno;
	
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
		List<Container> nodeContainers = n.getContainers();
		List<Element> nodeElements = n.getElements();
		
		String incElements = "";
		String incContainers = "";

		String grpStr = String.format(
				"\t\tControlGetterGroup %s =\n\t\t\tnew ControlGetter%s" +
				"(\"%s\", coreData, %s\n\t\t\t\t.addControls(Arrays.asList(", 
				StringUtils.camelCase(name), 
				StringUtils.pascalCase(type), 
				StringUtils.pascalCase(name), 
				locStr);
		
		if(nodeContainers != null) {
			for (Container c : nodeContainers) {
				incContainers += c.getName() + ", ";						
			}	
		}
		
		if(nodeElements != null) {
			for (Element e : nodeElements) {				
				if(elements.containsKey(e.getElementName())==false) {
					elements.put(e.getElementName(), e.getElementString() + "\n");
//					elementStr += e.getElementString() + "\n";	
				}
				incElements += e.getElementName() + ", ";
			}	
		}
		
		grpStr += 
				StringUtils.removeTrailingComma(incContainers) + 
				StringUtils.removeTrailingComma(incElements) + "));\n";
		
		if(groups.containsKey(name)==false) {
			groups.put(name, grpStr);
		}
		
//		grpStr += StringUtils.removeTrailingComma(incContainers) + StringUtils.removeTrailingComma(incElements) + "));";
//		return elementStr + grpStr;
	}
	
	public Map<String, String> getGroups(){
		return groups;
	}
	public Map<String, String> getElements(){
		return elements;
	}
	public SiteMapAnnotation getAnnotation() {
		return anno;
	}
	
	
	
	
	
	public void addGroup(ControlData grp) {
		if(grp != null) {
			zzz_groups.add(grp);
			if(grp instanceof GroupDataChild == false) {
				zzz_values.add(grp);	
			}
		}		
	}
//	public void addVal(ControlData val) {
//		groups.add(val);
//	}

	public ControlDataFunction getFunctionBuildMyControls() throws InvalidArgumentException {
//		if(thereAreControls()) {
//			numControls = zzz_values.size();		
//			func = 
//				"\tprivate void buildMyControls() {\n" +
//				getElements() +
//				getGroups() +
//				"\t\tvar myControls =\n" +
//				"\t\t\tList.of(";
//		
//			for (ControlData v : zzz_values) {
//				numControls--;
//				addControlToFunction(v.getName());
//			}			
//			func += "\n\t\t\t);\n\t\tsuper.buildPanelControls(myControls);\n\t}";		
//		}else {
//			func = "\t\tprivate void buildMyControls() {}";
//		}	
//		return new ControlDataFunction(anno, func);
		return null;
	}
	
	private boolean thereAreControls() {
		return true;//TODO****************
	}
	/*
	 * this is getting the groups
	 * we have to add the elements for all groups first/
	 */
//	private String getElements() {
//		String ret = "";
//		
//		for (ControlData v : zzz_values) {
//			func += ((GroupData)v).getElements();
//		}			
//		
//		return (ret.length() > 0) ? ret += "\n" : ret;
//	}
//	private String getGroups() {
//		String ret = "";
//		for (ControlData d : zzz_groups) {
//			ret += d.getValue() + "\n";
//		}		
//		return (ret.length() > 0) ? ret += "\n" : ret;
//	}
	private void addControlToFunction(String cntrlString) {
		if(numControls > 0) {
			func += "\n\t\t\t\t" + cntrlString + ",";	
		}else {
			func += "\n\t\t\t\t" + cntrlString;
		}		
	}


}
