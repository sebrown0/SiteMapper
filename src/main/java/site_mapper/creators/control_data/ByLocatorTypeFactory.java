/**
 * 
 */
package site_mapper.creators.control_data;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ByLocatorTypeFactory {
	public static String getByType(String forType) {
		String ret = "";
		if(forType != null) {
			if(forType.equalsIgnoreCase("CSS")) {
				ret = "By.cssSelector";
			}else if(forType.equalsIgnoreCase("XPATH")){
				ret = "By.xpath";
			}else if(forType.equalsIgnoreCase("ID") ) {
				ret = "By.id";
			}	
		}		
		return ret;
	}
}
