/**
 * 
 */
package site_mapper.creators.control_data;

import site_mapper.jaxb.containers.Container;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class GroupDataTab extends GroupData {	
	public GroupDataTab(Container cont) {
		super(cont);			
	}
		
	@Override
	protected void setInitialData() {
		super.grp = String.format(
				"\t\tTabGroup %s = " +
				"new TabGroup(coreData, By.cssSelector(\"ul[class='nav nav-tabs']\"));\n\t\t%s", 
				varName, varName);				
	}
		
	@Override
	public String getControlDataValue() {
		return String.format("new ControlData(\"%s\", new ControlGetterTab(coreData, %s))", name, varName);
	}

	@Override
	protected void setVarName() {
		varName = "tab" + super.name;		
	}
}
