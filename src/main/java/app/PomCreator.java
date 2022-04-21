/**
 * 
 */
package app;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class PomCreator {
	/*
	 * IS THIS USED?
	 */
	
//	public static void createPoms(AutDataGetter withAutData) {
//		LogManager.getLogger(PomCreator.class).info("Attempting to create POMs");
//		XmlContent content = getXmlContent(withAutData.getXmlSource());		
//		createPomsFromSource(withAutData, content, resolveImports());		
//	}
//	
//	private static XmlContent getXmlContent(String xmlSource) {
//		SiteMapContentGetter<PomMapperApp> contentGetter = 
//				new SiteMapContentGetter<>(xmlSource, PomMapperApp.class);
//		
//		return (XmlContent) contentGetter.getContent().get();
//	}
//	
//	/*
//	 * TODO
//	 * We only have one component writer at present, but
//	 * if we add more we will have to get the required
//	 * imports from them also.
//	 */
//	private static FoundImports resolveImports() {
//		ComponentWriter componentWriter = new ComponentWriterJsPanelWithIFrame();
//		ImportResolver impResolver  = 
//				new ImportResolver(
//						"C:/Users/SteveBrown/eclipse-workspace/2021", "SiteMapper");
//		impResolver.resolveRequired(componentWriter.getRequiredImports());
//		return impResolver;
//	}
//	
//	private static void createPomsFromSource(AutDataGetter withAutData, XmlContent content, FoundImports imps) {
//		PomMapperProd mapper = new PomMapperProd(content, imps);
//		mapper.createPomsFromSource(
//				withAutData.getRootDir(), withAutData.getXmlSource());
//	}
	
}
