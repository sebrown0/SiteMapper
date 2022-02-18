/**
 * 
 */
package utils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class StringUtils {
	public static String replaceFwdSlashes(String str, String with) {
		String s = str.replace("/", with); 
		return s;
	}

	public static String camelCase(String str) {
		String res = "";
		if(str != null && str.length() > 0) {
			String firstChar = str.substring(0, 1).toLowerCase();
			res = firstChar + str.substring(1, str.length());
		} 
		return res;
	}
	
	public static String pascalCase(String str) {
		String res = "";
		if(str != null && str.length() > 0) {
			String firstChar = str.substring(0, 1).toUpperCase();
			res = firstChar + str.substring(1, str.length());
		} 
		return res;
	}
	
}
