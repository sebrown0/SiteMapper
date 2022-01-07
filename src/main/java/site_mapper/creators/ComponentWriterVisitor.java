/**
 * 
 */
package site_mapper.creators;

import java.io.IOException;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ComponentWriterVisitor extends ComponentWriter {	
	ComponentWriterVisitor setFileOutWriter(ClassWriterActions fileOut);
	ComponentWriterVisitor setElementClass(ElementClass elementClass);
	ComponentWriterVisitor setSiteMapInfo(SiteMapInfo siteMapInfo);
	void writeComponents() throws IOException;
}
