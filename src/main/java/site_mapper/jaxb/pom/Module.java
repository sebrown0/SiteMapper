package site_mapper.jaxb.pom;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.xml.bind.annotation.XmlAttribute;
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
 * Module found in the app XML.
 */
@XmlRootElement(name = "Module")
public class Module {	
	@XmlAttribute(name="name")
	private String name;
  @XmlElementWrapper(name="Menus")
  @XmlElement(name="Menu")
  private List<Menu> menus;
    
  private Logger logger = LogManager.getLogger(Module.class);
  
  public Module getModuleContainers(PackageHierarchy ph, final SiteMapInfo siteMap) {  	
  	PackageMaker.makeWithPackageInfo(siteMap, ph.reset().addCurrent(name));
  	logger.info("Found module [" + name + "]. Attempting to map menus");
		if(menus != null) {			
			menus.forEach(m -> {								 					
				m.getMenuContainers(siteMap, ph);
	  	});	
		}  	
		return this;
	}
    
  public String getName() {
      return name;
  }
	public List<Menu> getMenus() {
		return menus;
	}
	  
}
