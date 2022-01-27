package site_mapper.jaxb.pom;

import java.util.List;

import app.PomMapperVisitor;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.PackageMaker;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * JAXB class representing the top level of the site_map.xml.
 * 
 * This parses the XML_SOURCE and creates the 
 * required packages and POMs.
 * 
 * TODO: Logging for POM creation, i.e. use different log to App/Tests.
 */
@XmlRootElement(name = "SiteMap")
public class PomMapperApp {	
	@XmlElement(name="Info")
	private SiteMapInfo siteMapInfo;
	
	@XmlElementWrapper(name="Modules")
  @XmlElement(name="Module")
  private List<Module> modules;
	
	public void createProdPoms(final String XML_SOURCE) {
		//TODO
	}
			
	public void createTestPoms(final String XML_SOURCE, PomMapperVisitor visitor) {
		if(siteMapInfo != null) {
			siteMapInfo.setXmlSource(XML_SOURCE);
			visitor.setSiteMapInfo(siteMapInfo);
			createPoms(XML_SOURCE);	
		}else {
			//TODO LOG ERROR
		}
	}
	
	private void createPoms(final String XML_SOURCE) {		
		PackageHierarchy packageHierarchy = 
				new PackageHierarchy(siteMapInfo.getRootDir(), siteMapInfo.getParentPackage());
		
		PackageMaker.makeParentWithPackageInfo(siteMapInfo, packageHierarchy);
		for (Module module : modules) {
			module.getModuleContainers(packageHierarchy, siteMapInfo);
		}					
	}

	
	public SiteMapInfo getSiteMapInfo() {
		return siteMapInfo;
	}
}