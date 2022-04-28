/**
 * 
 */
package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class AppLogger {
	
	public static void logInfo(String msg, Class<?> clazz) {
		getLogger(clazz).info(msg);
	}
	
	private static Logger getLogger(Class<?> clazz) {
		return LogManager.getLogger(clazz);
	}
	
}
