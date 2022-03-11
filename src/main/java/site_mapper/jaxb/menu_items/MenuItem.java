/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import file.helpers.Formatter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.ComponentWriter;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.containers.MenuItemContainer;
import site_mapper.jaxb.pom.SiteMapInfo;

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
	
	@XmlElement(name="Type", namespace="MenuItem")
	private MenuItemType menuItemType;	
	@XmlElement(name="HeaderElements", namespace="MenuItem")
	private MenuItemContainer headerContainer;	
	@XmlElement(name="BodyElements", namespace="MenuItem")
	private MenuItemContainer bodyContainer;	
	@XmlElement(name="FooterElements", namespace="MenuItem")
	private MenuItemContainer footerContainer;
		
	private String menuPackageName;
	private String moduleName;	
	private SiteMapInfo siteMapInfo;
	private List<Container> allContainers;	
	private List<MenuItemContainer> menuItemContainers;	
		
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
//	@Override //ElementClass
//	public List<Container> getAllContainers() {
//		if(allContainers == null) {
//			allContainers = new ArrayList<>();
//			Stream
//				.of(headerContainer, bodyContainer, footerContainer)
//				.filter(s -> s != null)
//				.forEach(allContainers::add);
//		} 	
//		return allContainers;				
//	}
	@Override //ElementClass
	public List<Container> getAllContainers() {
		if(allContainers == null) {
			allContainers = new ArrayList<>();
			getMenuItemContainers()
				.forEach(
						itm -> { 
							var cont = itm.getItemContainer();
							if(cont != null) {
								allContainers.add(cont);
							}
						});
		} 	
		return allContainers;				
	}
	public List<MenuItemContainer> getMenuItemContainers(){
		if(menuItemContainers == null) {
			menuItemContainers = new ArrayList<>();
			Stream
				.of(headerContainer, bodyContainer, footerContainer)
				.filter(s -> s != null)
				.forEach(menuItemContainers::add);
		} 	
		return menuItemContainers;		
	}
	
	public Container getHeaderContainer() {
		return (headerContainer != null) ? headerContainer.getItemContainer() : null;
	}
	public Container getBodyContainer() {
		return (bodyContainer != null) ? bodyContainer.getItemContainer() : null;
	}
	public Container getFooterContainer() {
		return (footerContainer != null) ? footerContainer.getItemContainer() : null;
	}
	
	@Override //ElementClass
	public MenuItemContainer getHeader() {
		return headerContainer;
	}
	@Override //ElementClass
	public MenuItemContainer getBody() {
		return bodyContainer;
	}
	@Override //ElementClass
	public MenuItemContainer getFooter() {
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
	public TestElement setHeaderContainer(MenuItemContainer cont) {
		this.headerContainer = cont;
		return this;
	}
	@Override //TestElement
	public TestElement setBodyContainer(MenuItemContainer cont) {
		this.bodyContainer = cont;
		return this;
	}
	@Override //TestElement
	public TestElement setFooterContainer(MenuItemContainer cont) {
		this.footerContainer = cont;
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
	
//	public Tab getTab(String tabName) {
//		Tab res = null;
//		for (Tab t : tabs) {
//			if(t.getName().equalsIgnoreCase(tabName)) {
//				res = t;
//				break;
//			}
//		}
//		return res;
//	}
	@Override
	public String toString() {
		return String.format(
				"MenuItem [name=%s, packageName=%s, className=%s, menuItemType=%s, headerElements=%s, bodyElements=%s, footerElements=%s, menuPackageName=%s, moduleName=%s, siteMapInfo=%s]",
				name, packageName, className, menuItemType, headerContainer, bodyContainer, footerContainer, menuPackageName,
				moduleName, siteMapInfo);
	}
	
				
}
