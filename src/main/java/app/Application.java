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
public final class Application {
	private static final String ROOT = 			
			"C:/Users/SteveBrown/eclipse-workspace/2021/DTest";
	
	private static final String XML_SOURCE = 
			ROOT + "/src/main/resources/site_map/site_map.xml";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PomMapper mapper = new PomMapper(XML_SOURCE);		
		mapper.createProdPoms();
	}

}
