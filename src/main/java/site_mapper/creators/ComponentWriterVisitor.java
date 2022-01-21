/**
 * 
 */
package site_mapper.creators;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ComponentWriterVisitor extends ComponentInfo {	
	ComponentWriterVisitor setElementClass(ElementClass elementClass);
	ComponentWriterVisitor setSiteMapInfo(SiteMapInfo siteMapInfo);
}
