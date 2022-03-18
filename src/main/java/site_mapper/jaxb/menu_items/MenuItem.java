/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.ArrayList;
import java.util.List;

import file.helpers.Formatter;
import site_mapper.creators.ComponentWriter;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.node.Node;
import site_mapper.jaxb.node.ParentNode;
import site_mapper.jaxb.pom.SiteMapInfo;
import site_mapper.jaxb.tree.ContainerWalker;

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
 * Represents the ClassFile, i.e. SalaryDetails.java.
 */
public class MenuItem implements ElementClass, TestElement {
	private String name;
	private String packageName;
	private String className;	
	private String menuPackageName;
	private String moduleName;	
	private SiteMapInfo siteMapInfo;
	private List<Container> allContainers;	
	private List<Container> itemContainers;	
	private MenuItemType menuItemType;	
					
	/** 
	 * @param itemDetails 
	 * Get the item's details from the unmarshalled menu item, 
	 * i.e. left menu, top-right menu etc.
	 */
	public MenuItem(MenuItemDetails itemDetails) {
		this.name = itemDetails.getName();
		this.packageName = itemDetails.getPackageName();
		this.className = itemDetails.getClassName();
		this.itemContainers = itemDetails.getItemContainers();
		this.menuItemType = itemDetails.getMenuItemType();
	}
	
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
			if(itemContainers != null) {
				itemContainers.forEach(c ->	allContainers.addAll(getChildren(c)));	
			}			
		} 	
		return allContainers;				
	}
	
	private List<Container> getChildren(Container c) {
		Node n = new ParentNode(c);
		ContainerWalker walker = new ContainerWalker(n);
		return walker.traverseContainers();
	}
	
	
	private Container getItemContainer(String name) {
		Container res = null;
		if(itemContainers != null) {
			for(Container c : itemContainers) {
				if(c.getName().equalsIgnoreCase(name)) {
					res = c;
					break;
				}
			}
		}
		return res;
	}
	@Override //ElementClass
	public Container getHeaderContainer() {
		return getItemContainer("HeaderElements");
	}
	@Override //ElementClass
	public Container getBodyContainer() {
		return getItemContainer("BodyElements");
	}
	@Override //ElementClass
	public Container getFooterContainer() {
		return getItemContainer("FooterElements");
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
		
//	@Override
//	public String toString() {
//		return String.format(
//				"MenuItem [name=%s, packageName=%s, className=%s, menuItemType=%s, headerElements=%s, bodyElements=%s, footerElements=%s, menuPackageName=%s, moduleName=%s, siteMapInfo=%s]",
//				name, packageName, className, menuItemType, headerContainer, bodyContainer, footerContainer, menuPackageName,
//				moduleName, siteMapInfo);
//	}
					
}
