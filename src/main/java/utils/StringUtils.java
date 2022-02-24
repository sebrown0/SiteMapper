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
	public static String removeTrailingComma(String str) {
		String res = "";
		int toPos = -1;
		if(str != null && str.length() > 0) {
			int strLen = str.length()-1;
			int idx = strLen;
			while(idx >= 0) {
				toPos = idx;
				if(str.charAt(idx) == ',') {
					res = str.substring(0, toPos);
					break;
				}
				idx--;
			}
		}
		return (toPos > 0) ? res : str;
	}
	
	public static String replaceFwdSlashes(String str, String with) {
		String s = str.replace("/", with); 
		return s;
	}

	public static String asCamelCase(String str) {
		String res = "";
		if(str != null && str.length() > 0) {
			String firstChar = str.substring(0, 1).toLowerCase();
			res = firstChar + str.substring(1, str.length());
		} 
		return res;
	}
	
	public static String asPascalCase(String str) {
		String res = "";
		if(str != null && str.length() > 0) {
			String firstChar = str.substring(0, 1).toUpperCase();
			res = firstChar + str.substring(1, str.length());
		} 
		return res;
	}
	
}
