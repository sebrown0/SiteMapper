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
 * POJO for annotation @SiteMap
 */
public abstract class SiteMapAnnotation implements IndentedElement<SiteMapAnnotation> {	
	private String author;
	private String version;
	private String date;
	private int numTabs;
		
	public static final Pattern annotationPattern = Pattern.compile(".*@SiteMap.*");
	public static final Predicate<String> annotationTest = s -> (annotationPattern.matcher(s).find());
	
	public SiteMapAnnotation setAuthor(String author) {
		this.author = author;
		return this;
	}
	public SiteMapAnnotation setVersion(String version) {
		this.version = version;
		return this;
	}
	public SiteMapAnnotation setDate(String date) {
		this.date = date;
		return this;
	}

	@Override //IndentedElement
	public SiteMapAnnotation setIndent(int numTabs) {
		this.numTabs = numTabs;
		return this;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s@SiteMap(%s, %s, %s)",
				getIndent(),
				Formatter.getValuePair(author, "author"), 
				Formatter.getValuePair(version, "version"),
				Formatter.getValuePair(date, "date"));
	}

	public String getIndent() {
		return LineTabs.getTabStr(numTabs);
	}
	
	public static boolean isAnnotation(String line) {
		return (annotationTest.test(line)) ? true : false;
	}
	
}
