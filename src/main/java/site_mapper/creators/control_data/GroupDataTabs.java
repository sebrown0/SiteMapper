/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.List;

import file.class_file.body.ContainerMapper;
import site_mapper.jaxb.containers.Container;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class GroupDataTabs extends GroupData {
	private Container cont;
	private ControlDataFunctionBuilder funcBuilder;
	
	public GroupDataTabs(Container cont, ControlDataFunctionBuilder funcBuilder) {
		super(cont);
		this.cont = cont;
		this.funcBuilder = funcBuilder;
	}

	public GroupDataTabs getTabs() {
		if(cont != null) {
			cont.getContainers().forEach(c -> {
				ContainerMapper mapper = new ContainerMapper(funcBuilder);
				mapper.addContainers(List.of(cont));		
			});
		}
		
		return this;
	}
		
	@Override
	protected void setInitialData() {
		super.grp = String.format(
				"\t\tTabsGroup %s = " +
				"new TabsGroup(coreData, By.cssSelector(\"ul[class='nav nav-tabs']\"));\n\t\t%s", 
				varName, varName);				
	}
		
	@Override
	public String getControlDataValue() {
		return String.format("new ControlData(\"%s\", new ControlGetterTabGroup(coreData, %s))", name, varName);
	}

	@Override
	protected void setVarName() {
		varName = "grp" + super.name;		
	}
}
