/**
 * 
 */
package app;

import app.xml_content.PomMapperProd;
import app.xml_content.XmlContent;
import site_mapper.creators.component_writer.ComponentImports;
import site_mapper.creators.component_writer.ComponentWriter;
import site_mapper.creators.component_writer.ComponentWriterJsPanelWithIFrame;
import site_mapper.creators.imports.FoundImports;
import site_mapper.creators.imports.ImportResolver;
import site_mapper.jaxb.pom.PomMapperApp;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class Application {
	// C:\Users\SteveBrown\eclipse-workspace\2021\DakarHR-Library\src\main\java\library / library.object_models
	
	private static final String LIBRARY = 
			"C:/Users/SteveBrown/eclipse-workspace/2021/DakarHR-Library";
	
	private static final String ROOT = LIBRARY + "/src/main/java/library";	
//			"C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/java";
	
	private static final String XML_SOURCE = 
			LIBRARY + "/src/main/resources/xml/site_map.xml";
	
//	private static final String XML_SOURCE = 
//			"C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/xml/site_map.xml";
	
	/**
	 * @param args
	 * 	None
	 * TODO - XML_SOURCE as argument.
	 */
	public static void main(String[] args) { 	
		SiteMapContentGetter<PomMapperApp> contentGetter = 
				new SiteMapContentGetter<>(XML_SOURCE, PomMapperApp.class);
		XmlContent content = (XmlContent) contentGetter.getContent().get();
				
		PomMapperProd mapper = new PomMapperProd(content, resolveImports());
		mapper.createPomsFromSource(ROOT, XML_SOURCE);
	}
	
	private static FoundImports resolveImports() {		
		ImportResolver impResolver  = 
				new ImportResolver(
						"C:/Users/SteveBrown/eclipse-workspace/2021", "SiteMapper");
		
		impResolver.resolveRequired(new ComponentImports().getAll());
		
		return impResolver;
	}

}
