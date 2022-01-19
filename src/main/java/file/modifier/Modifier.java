/**
 * 
 */
package file.modifier;

import java.util.regex.Pattern;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Modifier {
	private String modifier;
	
	public static final Pattern startsWithModifierPattern = Pattern.compile("^(public |protected |private )");
	public static final Pattern modifierPattern = Pattern.compile(".*public.*|.*protected.*|.*private.*");

	public Modifier(String modifier) {
		this.modifier = modifier;
	}
	
	public static boolean startsWithValidModifier(String line) {
		boolean res = false;
		if(line != null) {
			int start = line.indexOf("p");
			if(start >= 0) {
				res = startsWithModifierPattern.matcher(line.substring(start)).find();	
			}				
		}
		return res;		
	}
	public static boolean isValidModifier(String modifier) {
		return modifierPattern.matcher(modifier).find();
	}

	public String getModifier() {
		return modifier;
	}
	
	@Override
	public String toString() {
		return modifier;
	}
}
