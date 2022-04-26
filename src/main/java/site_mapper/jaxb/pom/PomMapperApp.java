package site_mapper.jaxb.pom;

import java.util.List;

import app.xml_content.XmlContent;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * JAXB class representing the top level of the site_map.xml.
 * Used to create the site mapped classes.
 */
@XmlRootElement(name="SiteMap")
public class PomMapperApp implements XmlContent {	
	@XmlElement(name="Info")
	private SiteMapInfo siteMapInfo;
	
	@XmlElement(name="DynamicTestInfo")
	private DynamicTestInfo dynamicTestInfo;
	
	@XmlElementWrapper(name="Modules")
	@XmlElement(name="Module", namespace="Module")
  private List<Module> modules;
	
	@Override //XmlContent
	public List<Module> getModules() {
		return modules;
	}

	@Override //XmlContent
	public SiteMapInfo getSiteMapInfo() {
		return siteMapInfo;
	}

	@Override //XmlContent
	public DynamicTestInfo getDynamicTestInfo() {
		return dynamicTestInfo;
	}
}