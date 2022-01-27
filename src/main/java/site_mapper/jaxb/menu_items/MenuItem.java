/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.List;

import file.class_package.PackageSetter;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.ClassMakerDirector;
import site_mapper.creators.ComponentWriter;
import site_mapper.creators.PackageMaker;
import site_mapper.elements.Element;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;
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
public class MenuItem implements ElementClass, TestElement {
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="package")
	private String packageName;
	@XmlAttribute(name="class")
	private String className;	
	
	@XmlElement(name="Type")
	private MenuItemType menuItemType;
	
	@XmlElementWrapper(name="Elements")
	@XmlElement(name="Element")
	private List<Element> elements;	
	
	private String menuPackageName;
	private String moduleName;	
	private SiteMapInfo siteMapInfo;
	private PackageSetter packageSetter;
	
	/**
	 * 
	 * @param packageSetter
	 *  Set the class's package for test or production.
	 * @param siteMap
	 *  All the info from site_map.xml.
	 * @param ph
	 * 	The classes package hierarchy; create, get etc.
	 * @param moduleName
	 *  The module the class is in.
	 * 
	 */
	public void createPoms(
			PackageSetter packageSetter, 
			SiteMapInfo siteMap, 
			PackageHierarchy ph, 
			String moduleName) { 
					
		
		this.packageSetter = packageSetter;
		this.siteMapInfo = siteMap;
		this.moduleName = moduleName;
		this.menuPackageName = ph.getCurrent();
				
		boolean createPackage = createPackageForClassIfNecessary(siteMap, ph);
		createClass(ph);
		removeThisClassPackageFromHierarchy(createPackage, ph);		
	}
	private void createClass(PackageHierarchy ph) {
		ClassMakerDirector cm = new ClassMakerDirector(this, ph, packageSetter);
		cm.makeClass();
	}
	private boolean createPackageForClassIfNecessary(SiteMapInfo siteMap, PackageHierarchy ph) {
		if(packageName != null && packageName.length() > 0) {
			PackageMaker.makeWithPackageInfo(siteMap, ph.addCurrent(packageName));
			return true;			
		}
		return false;
	}
	private void removeThisClassPackageFromHierarchy(boolean packageWasAddedToHierarchy, PackageHierarchy ph) {
		if(packageWasAddedToHierarchy) {
			ph.removeCurrent();
		}		
	}
	
	@Override //ElementClass
	public String getClassName() {
		return className;
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
	public List<Element> getElements() {
		return elements;
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
		return (elements != null && elements.size() > 0) ? true : false;
	}
	public String getName() {
		return name;
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
	public TestElement setElements(List<Element> elements) {
		this.elements = elements;
		return this;
	}
	
}
