/**
 * 
 */
package helpers;
import java.util.Arrays;

import file.class_file.ClassFile;
import file.class_file.ClassFile.NewClassFileBuilder;
import file.class_package.TestPackageSetter;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.menu_items.JsPanelWithIFrame;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.menu_items.MenuItemType;
import site_mapper.jaxb.menu_items.TestElement;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.ElementFunction;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class NewTestClassFileBuilder {
	private ExistingTestClassFileBuilder fileBuilder;
	private JsPanelWithIFrame attributes;
	private MenuItemType menuItemType;	
	private ClassFile classFile;
	
	private static ElementCreation SAVE_BUTTON;
	private static ElementCreation SEARCH_BUTTON;
	private static ElementCreation EMP_CODE_TEXT_OUT;
	
	public static SiteMapInfo SITE_MAP_INFO;
	public static TestElement MENU_ITEM;
	
	public NewTestClassFileBuilder() {
		this.fileBuilder = new ExistingTestClassFileBuilder();

		setAttributes();
		setMenuItemType();
		setElements();
		setMenuItem();
		setClassFile();
	}
	
	public String BUILD_MY_CONTROLS_RES() {
		return
			fileBuilder.ANNO_STR() + "\n" + 
				"\tprivate void buildMyControls(){\n" +
				"\t\tvar myControls =\n" +
				"\t\t\tList.of(\n" +
				"\t\t\t\tnew ControlData(\"save\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='SAVE']\"))),\n" +
				"\t\t\t\tnew ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF1']\"))),\n" +
				"\t\t\t\tnew ControlData(\"code\", new ControlGetterTextOut(coreData, By.cssSelector(\"input[id='FORM_ID']\")))\n" +
				"\t\t\t);\n" +
				"\t\tsuper.buildPanelControls(myControls);\n" +
				"\t}";
	}
	
	private SiteMapInfo SITE_MAP_INFO() {
		return new SiteMapInfo()
			.setAuthor(fileBuilder.AUTHOR)
			.setVersion(fileBuilder.VERSION)
			.setXmlSource(fileBuilder.XML_SOURCE)
			.setRootDir("C:/Users/SteveBrown/eclipse-workspace/2021/SiteMapper")
			.setElementLibrary("C:/Users/SteveBrown/eclipse-workspace/2021/DTest")
			.setDate(fileBuilder.DATE)
			.setTime(fileBuilder.TIME);
	}
	
	private void setAttributes() {
		attributes = new JsPanelWithIFrame();		
		attributes.setPanelTitle("Employee Details");
	}
	private void setMenuItemType() {
		menuItemType = new MenuItemType();
		menuItemType.setType("JsPanelWithIFrame");
		menuItemType.setAttributes(attributes);
	}
	private void setElements() {
		SAVE_BUTTON = new Element()
				.setType("button").setName("save").setBy("css")
				.setLocator("button[name='SAVE']")
				.setelementFunction(
						new ElementFunction()
							.setName("save").setType("button").isDefaultPass(true));
		
		SEARCH_BUTTON = new Element()
				.setType("button").setName("search").setBy("css")
				.setLocator("button[name='QBF1']")
				.setelementFunction(
						new ElementFunction()
							.setName("save").setType("button").isDefaultPass(false));
		
		EMP_CODE_TEXT_OUT = new Element().setType("text_out").setName("code").setBy("css").setLocator("input[id='FORM_ID']");
	}
	private void setMenuItem() {
		MENU_ITEM = new MenuItem();
		MENU_ITEM
			.setName("Employee Details")
			.setTestPackage("employees")
			.setTestClassName("EmployeeDetails")
			.setTestItemType(menuItemType)
			.setSiteMapInfo(this.SITE_MAP_INFO())
			
			.setElements(
				Arrays.asList(
					(Element)SAVE_BUTTON, 
					(Element)SEARCH_BUTTON, 
					(Element)EMP_CODE_TEXT_OUT));
	}
	private void setClassFile() {
		classFile = new NewClassFileBuilder((ElementClass) MENU_ITEM, new TestPackageSetter()).build();
	}

	public ClassFile getClassFile() {
		return classFile;
	}
	
}
