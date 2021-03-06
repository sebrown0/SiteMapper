package employees;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import org.openqa.selenium.By;
import control_builder.*;
import site_mapper.annotations.SiteMap;
import org.junit.jupiter.api.DynamicTest;
import dynamic_tests.annotations.TestControl;
import object_models.panels.JsPanelWithIFrame;
import object_models.pages.homepage.CoreData;

/**
* Generated Class.
* ----------------
* Source:  C:/site_map.xml
* Author:  SteveBrown
* Version: 1.0.0
* Created: 07/01/2022 08:53:56
*/

public class EmployeeDetails extends JsPanelWithIFrame {
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/01/2022")
	public static final String PANEL_TITLE = "Employee Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/01/2022")
	public static final String MENU_TITLE = "Employee Details";
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/01/2022")
	public static final String MENU_PARENT_NAME = "Employees";

	@SiteMap(author="SteveBrown", version="1.0.0", date="07/01/2022")
	public EmployeeDetails(CoreData coreData) {
		super(coreData, PANEL_TITLE);
		buildMyControls();
	}

	@SiteMap(author="SteveBrown", version="1.0.0", date="07/01/2022")
	private void buildMyControls(){
		var myControls = 
			List.of(
				new ControlData("save", new ControlGetterButton(coreData, By.cssSelector("button[name='SAVE']"))),
				new ControlData("search", new ControlGetterButton(coreData, By.cssSelector("button[name='QBF1']"))),
				new ControlData("code", new ControlGetterTextOut(coreData, By.cssSelector("input[id='FORM_ID']")))
			);
		super.buildPanelControls(myControls);
	}
	
	private String aMethodNotFromSiteMapper(int idx){
		String aStr = "";
		//do some stuff...
		
		return aStr;
	}
	
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/01/2022")
	@TestControl(type="button")
	public DynamicTest buttonSave () {
		return DynamicTest.dynamicTest("[buttonSave] *NOT IMPLEMENTED*", () -> assertTrue(true));
	}
	@SiteMap(author="SteveBrown", version="1.0.0", date="07/01/2022")
	@TestControl(type="button")
	public DynamicTest buttonSearch () {
		return DynamicTest.dynamicTest("[buttonSearch]", () -> fail("*NOT IMPLEMENTED*"));
	}
	
	
}