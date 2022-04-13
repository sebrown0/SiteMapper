/**
 * 
 */
package app;

import org.apache.logging.log4j.LogManager;

import app.xml_content.PomMapperProd;
import app.xml_content.XmlContent;
import site_mapper.jaxb.pom.PomMapperApp;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class PomCreator {
	
	public static void createPoms(AutDataGetter withAutData) {
		LogManager.getLogger(PomCreator.class).info("Attempting to create POMs");
		XmlContent content = getXmlContent(withAutData.getXmlSource());
		createPomsFromSource(withAutData, content);		
	}
	
	private static XmlContent getXmlContent(String xmlSource) {
		SiteMapContentGetter<PomMapperApp> contentGetter = 
				new SiteMapContentGetter<>(xmlSource, PomMapperApp.class);
		return contentGetter.getContent().get();
	}
	
	private static void createPomsFromSource(AutDataGetter withAutData, XmlContent content) {
		PomMapperProd mapper = new PomMapperProd(content);
		mapper.createPomsFromSource(
				withAutData.getRootDir(), withAutData.getXmlSource());
	}
	
}
