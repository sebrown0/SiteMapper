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
public class GroupDataRow extends GroupData {
	public GroupDataRow(Container cont) {
		super(cont);
	}

	@Override
	protected void setInitialData() {
		super.grp = String.format(
				"\t\tRow %s = new Row(coreData);\n\t\t%s", 
				varName, varName);				
	}
		
	@Override
	public String getControlDataValue() {
		return String.format("new ControlData(\"%s\", new ControlGetterRow(coreData, %s))", name, varName);
	}

	@Override
	protected void setVarName() {
		varName = "row" + super.name;		
	}

}
