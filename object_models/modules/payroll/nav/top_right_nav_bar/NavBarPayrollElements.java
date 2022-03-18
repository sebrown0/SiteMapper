package object_models.modules.payroll.nav.top_right_nav_bar;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.openqa.selenium.WebDriver;
import object_models.modules.pages.homepage.CoreData;
import object_models.modulestop_right_nav_bar.quick_links.QuickLinks;
import object_models.modulestop_right_nav_bar.quick_links.QuickLinksPayroll;

import object_models.modules.top_right_nav_bar.all_elements.NavBarEmployeeCreation 
public class NavBarPayrollElements implements NavBarElementStrategy {
	private Map<String, NavBarElement> elements;
	private WebDriver driver;
	private CoreData coreData;

	public NavBarPayrollElements(CoreData coreData) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		setElements();
	}

	private void setElements(){
		elements = Stream.of(new Object[][] {{
			EmployeeCreation, new NavBarEmployeeCreation(coreData)}
		}).collect(Collectors.toMap(data -> (String) data[0], data -> (NavBarElement) data[1])); 
	}

	@Override
	public Map<String, NavBarElement> getElements() {
		return elements;
	}

	@Override
	public QuickLinks getQuickLinks() {
		return new QuickLinksPayroll(driver);
	}
}