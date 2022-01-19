/**
 * 
 */
package file.annotation;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import file.helpers.Formatter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for annotation @SiteMap
 */
public abstract class SiteMapAnnotation {	
	private String author;
	private String version;
	private String date;
		
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

	@Override
	public String toString() {
		return String.format(
				"@SiteMap(%s, %s, %s)", 
				Formatter.getValuePair(author, "author"), 
				Formatter.getValuePair(version, "version"),
				Formatter.getValuePair(date, "date"));
	}
	
	public static boolean isAnnotation(String line) {
		return (annotationTest.test(line)) ? true : false;
	}
	
}
