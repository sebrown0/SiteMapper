/**
 * 
 */
package utils;

import java.util.regex.Pattern;

import logging.AppLogger;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ImportHelper {
	
	public static String getClassFromImportStr(String impStr) {
		String res = null;
		if(impStr != null && impStr.startsWith("import ")) {
			String[] parts;
			if(impStr.contains(".")) {
				parts = impStr.split(Pattern.quote("."));
			}else {
				parts = impStr.split(" ");							
			}
			res = parts[parts.length-1].replace(";", "");
		}else {
			AppLogger.logInfo(String.format(
					"Malformed import string [%s]. Cannot find import from it", 
					impStr), ImportHelper.class);
		}
		return res;
	}
	
}
