/**
 * 
 */
package file.annotation;

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
	
	public SiteMapAnnotation(String author, String version, String date) {		
		this.author = author;
		this.version = version;
		this.date = date;
	}
	
	@Override
	public String toString() {
		return String.format(
				"@SiteMap(%s, %s, %s)", 
				Formatter.getValuePair(author, "author"), 
				Formatter.getValuePair(version, "version"),
				Formatter.getValuePair(date, "date"));
	}
	
}
