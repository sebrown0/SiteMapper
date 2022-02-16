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
public class GroupDataInput extends GroupData {	
	public GroupDataInput(Container cont) {
		super(cont);
	}

	@Override
	protected void setInitialData() {
		super.grp = String.format(
				"\t\tInputGroup %s = " +
				"new InputGroup(coreData, By.cssSelector(\"div[class='input-group']\"));\n\t\t%s", 
				varName, varName);				
	}
		
	@Override
	public String getControlDataValue() {
		return String.format("new ControlData(\"%s\", new ControlGetterInputGroup(coreData, %s))", name, varName);
	}

	@Override
	protected void setVarName() {
		varName = "grp" + super.name;		
	}
}
