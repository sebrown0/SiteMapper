/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.ClassMaker;
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
 */
@XmlRootElement(name="MenuItem")
public class MenuItem implements ElementClass {
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
	
	public void createPoms(SiteMapInfo siteMap, PackageHierarchy ph){
		this.siteMapInfo = siteMap;
		boolean createPackage = createPackageForClassIfNecessary(siteMap, ph);
		createClass(ph);
		removeThisClassPackageFromHierarchy(createPackage, ph);		
	}
	private void createClass(PackageHierarchy ph) {
		ClassMaker cm = new ClassMaker(this, ph);
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
	
	public String getName() {
		return name;
	}
	
	public MenuItem setName(String name) {
		this.name = name;
		return this;
	}
	public MenuItem setPackageName(String packageName) {
		this.packageName = packageName;
		return this;
	}
	public MenuItem setClassName(String className) {
		this.className = className;
		return this;
	}
	public MenuItem setMenuItemType(MenuItemType menuItemType) {
		this.menuItemType = menuItemType;
		return this;
	}
	public MenuItem setElements(List<Element> elements) {
		this.elements = elements;
		return this;
	}	
	public MenuItem setSiteMapInfo(SiteMapInfo info) {
		this.siteMapInfo = info;
		return this;
	}	
}
