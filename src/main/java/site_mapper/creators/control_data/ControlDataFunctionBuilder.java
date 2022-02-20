/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidArgumentException;
import file.annotation.SiteMapAnnotation;

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
	private List<ControlData> groups;
	private List<ControlData> values;
	private String func = "";
	private int numControls;
	private SiteMapAnnotation anno;
	
	public ControlDataFunctionBuilder(SiteMapAnnotation anno) {		
		this.anno = anno;
		
		groups = new ArrayList<>();
		values = new ArrayList<>();
	}
	
	public void addGroup(ControlData grp) {
		if(grp != null) {
			groups.add(grp);
			if(grp instanceof GroupDataChild == false) {
				values.add(grp);	
			}
		}		
	}
	public void addVal(ControlData val) {
		groups.add(val);
	}

	public ControlDataFunction getFunctionBuildMyControls() throws InvalidArgumentException {
		if(thereAreControls()) {
			numControls = values.size();		
			func = 
				"\tprivate void buildMyControls() {\n" +
				getGroups() +
				"\t\tvar myControls =\n" +
				"\t\t\tList.of(";
		
			for (ControlData v : values) {
				numControls--;
				addControlToFunction(v.getName());
			}			
			func += "\n\t\t\t);\n\t\tsuper.buildPanelControls(myControls);\n\t}";		
		}else {
			func = "\t\tprivate void buildMyControls() {}";
		}	
		return new ControlDataFunction(anno, func);
	}
	
	private boolean thereAreControls() {
		return true;
	}
	
	private String getGroups() {
		String ret = "";
		for (ControlData d : groups) {
			ret += d.getValue() + "\n";
		}		
		return (ret.length() > 0) ? ret += "\n" : ret;
	}
	
	private void addControlToFunction(String cntrlString) {
		if(numControls > 0) {
			func += "\n\t\t\t\t" + cntrlString + ",";	
		}else {
			func += "\n\t\t\t\t" + cntrlString;
		}		
	}

}
