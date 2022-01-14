/**
 * 
 */
package file.comment;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ExistingComment extends Comment {
	
	@Override
	public String toString() {
		String result = "";
		for (String s : lines.getLines()) {
			result += s + "\n";
		}
		return result;
	}	
}
