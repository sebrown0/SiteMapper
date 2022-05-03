/**
 * 
 */
package app.xml_content;

import logging.AppLogger;
import site_mapper.creators.imports.ImportMatcher;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class PomMapperProd extends PomMapper {	
	public PomMapperProd(XmlContent content, ImportMatcher impMatcher) {
		super(content, impMatcher);
	}
		
	public void createPomsFromSource(final String ROOT_DIR, final String XML_SOURCE) {
		if(info != null) {
			info.setXmlSource(XML_SOURCE);
			info.setRootDir(ROOT_DIR);
			setPackageHierarchy();
			makePackage();
			createPoms(XML_SOURCE);	
		}else {
			AppLogger.logError("SiteMap Info must not be null to create POMs", getClass());
		}
	}

}
