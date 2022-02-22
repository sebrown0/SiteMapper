/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.Map;

import exceptions.InvalidArgumentException;
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
//		this.funcStr = funcStr;
	}
	
	public String getFunctionBuildMyControls() {
		String func = "";
		Map<String, String> elements = builder.getElements();
		Map<String, String> groups = builder.getGroups();
		
		if(elements.size()>0 || groups.size()>0) {
			func = 
				"\tprivate void buildMyControls() {\n" +
				getElements(elements) +
				getGroups(groups) +
				"\t\tvar myControls =\n" +
				"\t\t\tList.of(";
					
			func += "\n\t\t\t);\n\t\tsuper.buildPanelControls(myControls);\n\t}";		
		}else {
			func = "\t\tprivate void buildMyControls() {}";
		}	
		
		return func;
	}
	private String getElements(Map<String, String> elements) {		
		return getMapValues(elements);
	}
	private String getGroups(Map<String, String> groups) {
		return getMapValues(groups);
	}
	private String getMapValues(Map<String, String> map) {
		String res = "";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			res += entry.getValue();
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
