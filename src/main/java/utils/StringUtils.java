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
}
