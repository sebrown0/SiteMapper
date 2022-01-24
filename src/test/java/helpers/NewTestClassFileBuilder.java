/**
 * 
 */
package helpers;
import static helpers.ExistingTestClassFileBuilder.*;

import java.util.Arrays;

import file.class_file.ClassFile;
import file.class_file.ClassFile.NewClassFileBuilder;
import site_mapper.elements.Element;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementCreation;
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
	private static ElementCreation SAVE_BUTTON;
	private static ElementCreation SEARCH_BUTTON;
	private static ElementCreation EMP_CODE_TEXT_OUT;
	
	public static SiteMapInfo SITE_MAP_INFO;
	public static TestElement MENU_ITEM;
	
	static {
		JsPanelWithIFrame attributes = new JsPanelWithIFrame();		
		attributes.setMenuParentName("Employees");
		attributes.setMenuTitle("Employee Details");
		attributes.setPanelTitle("Employee Details");
		
		MenuItemType menuItemType = new MenuItemType();
		menuItemType.setType("JsPanelWithIFrame");
		menuItemType.setAttributes(attributes);
		
		SITE_MAP_INFO = new SiteMapInfo();
		SITE_MAP_INFO
			.setAuthor(AUTHOR)
			.setVersion(VERSION)
			.setXmlSource(XML_SOURCE)
			.setRootDir("C:/Users/SteveBrown/eclipse-workspace/2021/SiteMapper")
			.setElementLibrary("C:/Users/SteveBrown/eclipse-workspace/2021/DTest")
			.setDate(DATE)
			.setTime(TIME);
		
		/*
		 * create elements and add to menuItem below.......
		 */
		SAVE_BUTTON = new Element().setName("save").setBy("css").setLocator("\"button[name='SAVE']\"");
		SEARCH_BUTTON = new Element().setName("search").setBy("css").setLocator("\"button[name='QBF1']\"");
		EMP_CODE_TEXT_OUT = new Element().setName("code").setBy("css").setLocator("\"input[id='FORM_ID']\"");
		
		MENU_ITEM = new MenuItem();
		MENU_ITEM
			.setTestPackage("a.payroll.Left.employees;")
			.setTestClassName("EmployeeDetails")
			.setTestItemType(menuItemType)
			.setSiteMapInfo(SITE_MAP_INFO)
			.setElements(Arrays.asList((Element)SAVE_BUTTON, (Element)SEARCH_BUTTON, (Element)EMP_CODE_TEXT_OUT));
		
		classFile = new NewClassFileBuilder((ElementClass) MENU_ITEM).build();
	}

	public static ClassFile getClassFile() {
		return classFile;
	}
	
}
