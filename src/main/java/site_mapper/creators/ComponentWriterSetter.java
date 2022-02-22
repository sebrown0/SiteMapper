/**
 * 
 */
package site_mapper.creators;

import file.imports.Import;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ComponentWriterSetter extends ComponentWriter {	
	ComponentWriterSetter setElementClass(ElementClass elementClass);
	ComponentWriterSetter setSiteMapInfo(SiteMapInfo siteMapInfo);
}
