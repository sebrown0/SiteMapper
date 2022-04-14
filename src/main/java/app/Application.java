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
			"C:/Users/SteveBrown/eclipse-workspace/2021/AUT_DECOUPLE/src/main/java";
	
	private static final String XML_SOURCE = 
			"C:/Users/SteveBrown/eclipse-workspace/2021/AUT_DECOUPLE/src/main/resources/xml/site_map.xml";
	
	/**
	 * @param args
	 * 	None
	 * TODO - XML_SOURCE as argument.
	 */
	public static void main(String[] args) {
		SiteMapContentGetter<PomMapperApp> contentGetter = 
				new SiteMapContentGetter<>(XML_SOURCE, PomMapperApp.class);
		XmlContent content = contentGetter.getContent().get();
				
		PomMapperProd mapper = new PomMapperProd(content);
		mapper.createPomsFromSource(ROOT, XML_SOURCE);		

	}

}
