/**
 * 
 */
package app.xml_content;

import app.PomMapperVisitor;
import site_mapper.creators.imports.ImportMatcher;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class PomMapperTest extends PomMapper {
	private PomMapperVisitor visitor;
		
	public PomMapperTest(PomMapperVisitor visitor, XmlContent content, ImportMatcher impMatcher) {	
		super(content, impMatcher);
		this.visitor = visitor;
	}
	
	@Override
	public void createPomsFromSource(final String ROOT_DIR, final String XML_SOURCE) {
		if(info != null) {
			info.setXmlSource(XML_SOURCE);
			info.setRootDir(ROOT_DIR);
			visitor.setSiteMapInfo(info);
			setPackageHierarchy();
			makePackage();
			createPoms(XML_SOURCE);	
		}else {
			//TODO LOG ERROR
		}
	}
	
}
