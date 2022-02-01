/**
 * 
 */
package file.annotation;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import file.helpers.Formatter;
import file.helpers.IndentedElement;
import file.helpers.LineTabs;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for annotation @TestControl
 * Type is automatically got from the annotation string.
 * 
 */
public class TestMethodAnnotation implements IndentedElement<TestMethodAnnotation> {
	private String annoStr;	
	private int numTabs;
		
	private static final Pattern annotationPattern = Pattern.compile(".*@TestControl.*");
	public static final Predicate<String> annotationTest = s -> (annotationPattern.matcher(s).find());

	public TestMethodAnnotation(String annoStr) {
		this.annoStr = annoStr;
	}

	@Override //IndentedElement
	public TestMethodAnnotation setIndent(int numTabs) {
		this.numTabs = numTabs;
		return this;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s@TestControl(%s)\n",
				getIndent(),
				Formatter.getValuePair(getType(), "type"));
	}

	private String getIndent() {
		return LineTabs.getTabStr(numTabs);
	}
	
	private String getType() {
		String type = "UNKNOWN";
		if(annoStr != null && annoStr.contains("\"")) {
			String[] parts = annoStr.split("\\\"");
			type = parts[1];	
		}		 	
		return type;
	}
	
}
