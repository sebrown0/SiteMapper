package app.xml_content;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Menu;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.SiteMapInfo;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * JAXB class representing the top level element root
 * for dynamic tests.
 * 
 */
@XmlRootElement(name="SiteMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class DynamicTestApp implements XmlContent, XmlTestContent {	
	@XmlElement(name="Info")
	private SiteMapInfo siteMapInfo;
	
	@XmlElementWrapper(name="IncludeElementsForTest")
	@XmlElement(name="Include", namespace="IncludeElementsInTest")
	private List<String> includeElementsForTest;
	
	@XmlElementWrapper(name="Modules")
  @XmlElement(name="Module")
  private List<Module> modules;

	@Override //XmlContent
	public List<Module> getModules() {
		return modules;
	}

	@Override //XmlContent
	public SiteMapInfo getSiteMapInfo() {
		return siteMapInfo;
	}
	
	@Override //XmlTestContent
	public List<String> getIncludeElementsForTest() {
		return includeElementsForTest;
	}
		
	@Override 
	public String toString() {
		return String.format("Info[%s], modules%s",  siteMapInfo.toString(), modulesToString());
	}
	
	private String modulesToString() {
		String els = "\n";
		if(modules != null) {
			for(Module m: modules) {				
				els += "\n" + m.getName();
				els += "\n" + getMenus(m);
			}
		}
		return els;
	}
	
	private String getMenus(Module m) {
		String res = "\n  Menus\n  -----";
		for(Menu men: m.getMenus()) {
			res += "\n  " + men.getName();
			res += "\n   Items\n   -----" + getMenuItems(men);
		}
		return res;
	}
	
	private String getMenuItems(Menu men) {
		String res = "";
		for(MenuItem item: men.getMenuItems()) {
			res += "\n   " + item.getName() + "(type: " + item.getMenuItemType().getType() + ")" ;
//			res += "\n   " + item.getName();
			res += "\n    Containers\n    ----------" + getItemContainers(item);
		}
		return res;
	}
	
	private String getItemContainers(MenuItem item) {
		String res = "";
//		Container hdr = item.getHeader();
//		res += "\n    " + hdr.getName();
//		res += "\n     Elements\n     --------" + hdr.getName();
//		res += "\n     " + getElements(hdr);
		
//		for(Container c: item.getAllContainers()) {
//			res += "\n    " + c.getName();
//		} 	
		return res;
	}
	
	private String getElements(Container c) {
		String res = "";
//		for(Element e : c.getElements()) {
//			res += "\n     " + e.getElementName();
//		}
		return res;
	}
//	**********************
//	* THIS GOES IN DTest *
//  **********************
	
	
//	public DynamicContainer getTests() {
////		includeElementsForTest.forEach(c -> System.out.println("->" + c));		
//		List<DynamicContainer> appModules = new ArrayList<>();		
//		if(homepageOk() && modules != null) {			
//			for (Module module : modules) {
//				appModules.add(module.getModuleContainers(new IncludedTests(includeElementsForTest), homePage));
//			}			
//		}else {
//			LogManager.getLogger().error("Homepage or modules is null. Cannot run tests");			
//		}
//		return DynamicContainer.dynamicContainer("App", appModules);		
//	}
//		 
//	private boolean homepageOk() {
//		return (homePage != null) ? true : false;
//	}
//	public DynamicTestApp setHomePage(HomePage homePage) {
//		this.homePage = homePage;
//		return this;
//	}
	
}