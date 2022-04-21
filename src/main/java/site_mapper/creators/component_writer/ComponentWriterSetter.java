/**
 * 
 */
package site_mapper.creators.component_writer;

import site_mapper.creators.imports.FoundImports;
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
	ComponentWriterSetter setFoundImports(FoundImports imps);	
}
