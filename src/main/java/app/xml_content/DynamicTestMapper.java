/**
 * 
 */
package app.xml_content;

/**
 * @author Brown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DynamicTestMapper {
	/*
	 * *************************
	 * THIS SHOULD BE IN DTest *
	 * *************************
	 * 
	 * WHICH WILL REQUIRE A METHOD FROM SiteMapper TO
	 * GET THE XmlContent [DynamicTestApp].
	 * 
	 */
	
	public DynamicContainer getTests() {
//includeElementsForTest.forEach(c -> System.out.println("->" + c));		
	List<DynamicContainer> appModules = new ArrayList<>();		
	if(homepageOk() && modules != null) {			
		for (Module module : modules) {
			appModules.add(module.getModuleContainers(new IncludedTests(includeElementsForTest), homePage));
		}			
	}else {
		LogManager.getLogger().error("Homepage or modules is null. Cannot run tests");			
	}
	return DynamicContainer.dynamicContainer("App", appModules);		
	}

}
