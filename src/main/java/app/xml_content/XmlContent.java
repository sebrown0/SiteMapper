/**
 * 
 */
package app.xml_content;

import java.util.List;

import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface XmlContent {
	SiteMapInfo getSiteMapInfo();
	List<Module> getModules();	
}
