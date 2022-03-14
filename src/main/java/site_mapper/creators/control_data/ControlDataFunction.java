/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.List;

import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlDataFunction {
	private SiteMapAnnotation annotation;
	private ControlDataFunctionBuilder builder;
	private String funcStr;
	
	public ControlDataFunction(ControlDataFunctionBuilder builder) {
		this.annotation = builder.getAnnotation();
		this.builder = builder;
	}
	
	public String getFunctionBuildMyControls() {
		String func = "";
		List<String> elements = builder.getElements();
		List<String> groups = builder.getGroups();
		
		if(elements.size()>0 || groups.size()>0) {
			func = 
				"\t" + annotation.toString() +	
				"\n\tprivate void buildMyControls() {\n" +
				getElements(elements) +
				getGroups(groups) +
				"\t\tvar myControls =\n" +
				"\t\t\tList.of(" +
				builder.getInMyControls() +  
				"\n\t\t\t);";
					
			func += "\n\t\tsuper.buildPanelControls(myControls);\n\t}";		
		}else {
			func = "\t\tprivate void buildMyControls() {}";
		}	
		
		return func;
	}
	
	private String getElements(List<String> elements) {		
		String res = "";
		for (String s : elements) {
			res += s;
		}
		return res;
	}
	
	private String getGroups(List<String> groups) {
		String res = "";
		var numEntries = groups.size() - 1;
		for (var idx = numEntries; idx >= 0;  idx--) {
			res += groups.get(idx);
		}
		return res;
	}

	@Override
	public String toString() {		
		return String.format(
				"%s%s", 
				Formatter.getAnnotation(annotation),
				funcStr);
	}
	
}
