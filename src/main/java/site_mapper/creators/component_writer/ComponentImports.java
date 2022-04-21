/**
 * 
 */
package site_mapper.creators.component_writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * All the imports that are required in any site-mapped
 * class should be listed in here.
 * 
 */
public class ComponentImports {
	private List<String> all = new ArrayList<>();
	
	public List<String> getControls(){
		return
				Arrays.asList(
					"ControlGetterInputGroup", "ControlGetter", "ControlGetterGroup", 
					"ControlGetterTextOut", "ControlGetterButton", "ControlGetterTabs",
					"ControlGetterLabel", "ControlGetterTab");
	}
	
	public List<String> getForJsPanel(){
		return
				Arrays.asList(
					"TestControl", "ControlGetter", "ControlGetterGroup", 
					"JsPanelWithIFrame", "ControlData", "CoreData");
	}
	
	public List<String> getAll(){
		addListToAll(getControls());
		addListToAll(getForJsPanel());
		return all;				
	}
	
	private void addListToAll(List<String> lst) {		
		all.addAll(getOnlyMissing(lst));		
	}
	
	private List<String> getOnlyMissing(List<String> lst) {
		return lst.stream()
			.filter(f ->  { return (all.contains(f)) ? false : true; } )
			.collect(Collectors.toList());
	}
	
}
