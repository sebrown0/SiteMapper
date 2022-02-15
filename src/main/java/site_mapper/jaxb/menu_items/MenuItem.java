/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import file.helpers.Formatter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.ComponentWriter;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.ElementFunction;
import site_mapper.jaxb.pom.SiteMapInfo;
import site_mapper.jaxb.pom.Tab;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add moduleName.
 * @version 1.2
 * 	Put all the tests into a Map.
 * @since 1.0
 * 
 * Is the ClassFile.
 */
@XmlRootElement(name="MenuItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItem implements ElementClass, TestElement {
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="package")
	private String packageName;
	@XmlAttribute(name="class")
	private String className;	
	
	@XmlElement(name="Type")
	private MenuItemType menuItemType;
	
	@XmlElementWrapper(name="Tabs")
	@XmlElement(name="Tab")
	private List<Tab> tabs;	
	@XmlElement(name="HeaderElements")
	private Container headerContainer;	
	@XmlElement(name="BodyElements")
	private Container bodyContainer;	
	@XmlElement(name="FooterElements")
	private Container footerContainer;
	
	private String menuPackageName;
	private String moduleName;	
	private SiteMapInfo siteMapInfo;
	private List<Container> allContainers;	
	
	@Override //ElementClass
	public String getName() {
		return name;
	}
	@Override //ElementClass
	public String getClassName() {
		return Formatter.capitaliseFirstChar(className);
	}
	@Override //ElementClass
	public String getPackage() {
		return packageName;
	}
	@Override //ElementClass
	public String getParentPackage() {
		return menuPackageName;
	}
	@Override //ElementClass
	public String getModuleName() {
		return moduleName;
	}
	@Override //ElementClass
	public String getTypeName() {
		return menuItemType.getType();
	}
	@Override //ElementClass
	public MenuItemType getMenuItemType() {
		return menuItemType;
	}
	@Override //ElementClass
	public List<Container> getAllContainers() {
		if(allContainers == null) {
			allContainers = new ArrayList<>();
			Stream
				.of(headerContainer, bodyContainer, footerContainer)
				.filter(s -> s != null)
				.forEach(allContainers::add);
		} 	
		return allContainers;				
	}
	@Override //ElementClass
	public Container getHeader() {
		return headerContainer;
	}
	@Override //ElementClass
	public Container getBody() {
		return bodyContainer;
	}
	@Override //ElementClass
	public Container getFooter() {
		return footerContainer;
	}
	@Override //ElementClass
	public SiteMapInfo getSiteMapInfo() {
		return siteMapInfo;
	}	
	@Override //ElementClass
	public ComponentWriter getComponentWriter() {
		return menuItemType.getAttributes().getComponentWriter();
	}
	@Override //ElementClass
	public boolean hasControlList() {
		/*
		 * TODO - Not a definite way to see if there are controls.
		 * Would have to iterate through each container to see if
		 * there are any elements.
		 */
		List<Container> containers = getAllContainers();
		return (containers != null && containers.size() > 0) ? true : false;
	}
//	@Override //ElementClass
//	public List<ElementFunction> getElementFunctions() {
//		List<Container> elements = getAllContainers();
//		if(elements != null) {
//			List<ElementFunction> funcs = 
//					elements.stream()
//						.filter(f -> f.getElementFunction() != null)
//						.map(e -> e.getElementFunction())
//						.collect(Collectors.toList()); 
//				return funcs;				
//		}else {
//			return null;
//		}		
//	}
	@Override //ElementClass
	public TypeAttributes getTypeAttributes() {
		return menuItemType.getAttributes();
	}
	
	@Override //TestElement
	public TestElement setName(String str) {
		this.name = str;
		return this;
	}
	@Override //TestElement
	public TestElement setTestPackage(String str) {
		this.packageName = str;
		return this;
	}
	@Override //TestElement
	public TestElement setTestClassName(String str) {
		this.className = str;
		return this;
	}
	@Override //TestElement
	public TestElement setTestItemType(MenuItemType menuItemType) {
		this.menuItemType = menuItemType;
		return this;
	}
	@Override //TestElement
	public TestElement setSiteMapInfo(SiteMapInfo info) {
		this.siteMapInfo = info;
		return this;
	}
	@Override //TestElement
	public TestElement setElements(Container elements) {
		this.headerContainer = elements;
		return this;
	}
	@Override //TestElement
	public TestElement setTestModuleName(String str) {
		this.moduleName = str;
		return this;
	}
	@Override //TestElement
	public TestElement setTestMenuName(String str) {
		this.menuPackageName = str;
		return this;
	}
	@Override //TestElement
	public TestElement setTypeAttributes(TypeAttributes att) {
		this.menuItemType.setAttributes(att);
		return this;
	}
	
	public Tab getTab(String tabName) {
		Tab res = null;
		for (Tab t : tabs) {
			if(t.getName().equalsIgnoreCase(tabName)) {
				res = t;
				break;
			}
		}
		return res;
	}
	@Override
	public String toString() {
		return String.format(
				"MenuItem [name=%s, packageName=%s, className=%s, menuItemType=%s, tabs=%s, headerElements=%s, bodyElements=%s, footerElements=%s, menuPackageName=%s, moduleName=%s, siteMapInfo=%s]",
				name, packageName, className, menuItemType, tabs, headerContainer, bodyContainer, footerContainer, menuPackageName,
				moduleName, siteMapInfo);
	}
	
				
}
