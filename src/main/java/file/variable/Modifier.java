/**
 * 
 */
package file.variable;

import java.util.regex.Pattern;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Modifier {	
	public static final Pattern modifierPattern = Pattern.compile(".*public.*|.*protected.*|.*private.*");
	
	public static boolean isValidModifier(String modifier) {
		return modifierPattern.matcher(modifier).find();
	}
}
