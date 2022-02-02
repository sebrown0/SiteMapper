/**
 * 
 */
package app;

import app.xml_content.PomMapperProd;
import app.xml_content.XmlContent;
import site_mapper.jaxb.pom.PomMapperApp;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class Application {
	private static final String ROOT = 			
			"C:/Users/SteveBrown/eclipse-workspace/2021/DTest";
	
	private static final String XML_SOURCE = 
			ROOT + "/src/main/resources/site_map/site_map.xml";
	
		
	/**
	 * @param args
	 * 	None
	 * TODO - XML_SOURCE as argument.
	 */
	public static void main(String[] args) {
		SiteMapContentGetter<PomMapperApp> contentGetter = new SiteMapContentGetter<>(XML_SOURCE);
		XmlContent content = contentGetter.getContent(PomMapperApp.class).get();
		PomMapperProd mapper = new PomMapperProd(content);
		mapper.createPomsFromSource(XML_SOURCE);		
	}

}
