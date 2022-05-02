/**
 * 
 */
package site_mapper.creators.component_writer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import site_mapper.creators.imports.RequiredImports;

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
public class ComponentImports implements RequiredImports {
	private List<String> all = new ArrayList<>();
	
	public List<String> getCommon(){
		return Arrays.asList("Closable");
	}
	
	public List<String> getUtils(){
		return Arrays.asList("TextWriterComboMulti");
	}
	
	public List<String> getForNav(){
		return 
			Arrays.asList(
				"LeftMenuElements", "NavBarElement", "NavBarElementStrategy", 
				"QuickLinks", "QuickLinksPayroll");
		//"QuickLinksPersonnel"
	}
	
	public List<String> getControls(){
		return
				Arrays.asList(
					"ControlGetterInputGroup", "ControlGetter", "ControlGetterGroup", 
					"ControlGetterTextOut", "ControlGetterButton", 
					"ControlGetterTabs", "ControlGetterTab",
					"ControlGetterLabel", 
					"ControlGetterComboWriteAndSelect", "ControlGetterComboSelectOnly",
					"ControlGetterRow");
	}
	
	public List<String> getForJsPanel(){
		return
				Arrays.asList(
					"TestControl", "ControlGetter", "ControlGetterGroup", 
					"JsPanelWithIFrame", "ControlData", "CoreData");
	}
	
	public List<String> getAll(){
		addListToAll(getCommon());
		addListToAll(getUtils());
		addListToAll(getForNav());
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

	@Override
	public List<String> getRequiredImports() {
		return getAll();
	}

	@Override
	public void updateWithMatched(String imp) {
		//N/A
	}
	
}
