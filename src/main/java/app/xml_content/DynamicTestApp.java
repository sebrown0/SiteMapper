package app.xml_content;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement(name = "SiteMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class DynamicTestApp implements XmlContent {	
	@XmlElement(name="Info")
	private SiteMapInfo siteMapInfo;
	
	@XmlElementWrapper(name="IncludeElementsForTest")
	@XmlElement(name="Include")
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

	public List<String> getIncludeElementsForTest() {
		return includeElementsForTest;
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