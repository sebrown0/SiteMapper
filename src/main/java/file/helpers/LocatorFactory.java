/**
 * 
 */
package file.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import site_mapper.jaxb.pom.Locator;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class LocatorFactory {
	private static Logger logger = LogManager.getFormatterLogger(LocatorFactory.class);
	
	public static String getByString(Locator locator) {
		String res = "";
		String by = getByType(locator.getBy());
		if(by != null) {
			res = String.format("%s(%s)", by, locator.getLocator());
		}		
		return res;
	}
	
	private static String getByType(String forTypeStr) {
		String res = null;		
		if(forTypeStr.equals("css")) {
			res = "By.cssSelector";
		}else if (forTypeStr.equals("xpath")) {
			res = "By.xpath";
		}else if (forTypeStr.equals("id")) {
			res = "By.id";
		}else {
			logger.error("Get By type using[" + forTypeStr + "] not implemented");
		}		
		return res;
	}
	
}
