/**
 * 
 */
package site_mapper.creators.control_data;

import org.apache.logging.log4j.LogManager;

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
			}else {
				LogManager
					.getLogger(ByLocatorTypeFactory.class)
					.error("By Type for [" + forType + "] not implemented");
			}			
		}		
		return ret;
	}
}
