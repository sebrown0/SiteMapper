/**
 * 
 */
package helpers;
import static helpers.ExistingTestClassFileBuilder.*;
import file.class_file.ClassFile;
import file.class_file.ClassFile.NewClassFileBuilder;
import site_mapper.jaxb.menu_items.JsPanelWithIFrame;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.menu_items.MenuItemType;
import site_mapper.jaxb.menu_items.TestElement;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class NewTestClassFileBuilder {
	private static ClassFile classFile;
	
	static {
		JsPanelWithIFrame attributes = new JsPanelWithIFrame();		
		attributes.setMenuParentName("Employees");
		attributes.setMenuTitle("Employee Details");
		attributes.setPanelTitle("Employee Details");
		
		MenuItemType menuItemType = new MenuItemType();
		menuItemType.setType("JsPanelWithIFrame");
		menuItemType.setAttributes(attributes);
		
		SiteMapInfo info = new SiteMapInfo();
		info
			.setAuthor(AUTHOR)
			.setVersion(VERSION)
			.setXmlSource(XML_SOURCE)
			.setRootDir("C:/Users/SteveBrown/eclipse-workspace/2021/SiteMapper")
			.setElementLibrary("C:/Users/SteveBrown/eclipse-workspace/2021/DTest")
			.setDate(DATE)
			.setTime(TIME);

		TestElement menuItem = new MenuItem();
		menuItem
			.setTestPackage("package a.payroll.Left.employees;")
			.setTestClassName("EmployeeDetails ")
			.setTestItemType(menuItemType)
			.setSiteMapInfo(info);
		
		classFile = new NewClassFileBuilder(menuItem).build();
	}

	public static ClassFile getClassFile() {
		return classFile;
	}
	
}
