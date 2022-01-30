/**
 * 
 */
package app.xml_content;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class PomMapperProd extends PomMapper {	
	public PomMapperProd(XmlContent content) {
		super(content);
	}
		
	public void createPomsFromSource(final String XML_SOURCE) {
		if(info != null) {
			info.setXmlSource(XML_SOURCE);
			setPackageHierarchy();
			makePackage();
			createPoms(XML_SOURCE);	
		}else {
			//TODO LOG ERROR
		}
	}
	
}
