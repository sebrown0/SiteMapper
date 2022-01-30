/**
 * 
 */
package app.xml_content;

import app.PomMapperVisitor;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class PomMapperTest extends PomMapper {
	private PomMapperVisitor visitor;
		
	public PomMapperTest(PomMapperVisitor visitor, XmlContent content) {	
		super(content);
		this.visitor = visitor;
	}
	
	@Override
	public void createPomsFromSource(final String XML_SOURCE) {
		if(info != null) {
			info.setXmlSource(XML_SOURCE);
			visitor.setSiteMapInfo(info);
			setPackageHierarchy();
			makePackage();
			createPoms(XML_SOURCE);	
		}else {
			//TODO LOG ERROR
		}
	}
	
}
