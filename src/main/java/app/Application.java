/**
 * 
 */
package app;

import app.xml_content.PomMapperProd;
import app.xml_content.XmlContent;
import site_mapper.creators.component_writer.ComponentImports;
import site_mapper.creators.imports.ImportFinder;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.jaxb.pom.PomMapperApp;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class Application {
	/*
	 * NOTE
	 * ----
	 * After creating/updating the target library,
	 * we must run mvn clean, compile & install 
	 * on the library so that projects that depend
	 * on it get the updated version.
	 * 
	 * When we do the update it is up to us to decide
	 * on the version we use.
	 * 
	 * The target library & version that's used for
	 * dynamic tests is set in the DTest pom.xml.
	 */
	
	private static final String LIBRARY = 
			"C:/Users/SteveBrown/eclipse-workspace/2021/DakarHR-Library";
	
	private static final String ROOT = LIBRARY + "/src/main/java";
	
	private static final String XML_SOURCE = 
			LIBRARY + "/src/main/resources/xml/site_map.xml";
	
	
	/**
	 * @param args
	 * 	None
	 * TODO - XML_SOURCE as argument.
	 */
	public static void main(String[] args) { 	
		System.out.println("Starting"); // TODO - remove or log 	
		
		SiteMapContentGetter<PomMapperApp> contentGetter = 
				new SiteMapContentGetter<>(XML_SOURCE, PomMapperApp.class);
		XmlContent content = (XmlContent) contentGetter.getContent().get();
				
		PomMapperProd mapper = new PomMapperProd(content, findImports());
		mapper.createPomsFromSource(ROOT, XML_SOURCE);
		
		System.out.println("Finished"); // TODO - remove or log
	}
	
	private static ImportMatcher findImports() {		
		ImportFinder impFinder  = 
				new ImportFinder(
						"C:/Users/SteveBrown/eclipse-workspace/2021", 
						"SiteMapper",
						new ComponentImports().getAll());
		
		impFinder.findRequired();
		
		return new ImportMatcher(impFinder);		
	}

}
