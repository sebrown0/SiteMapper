/**
 * 
 */
package file.annotation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for existing annotation @SiteMap
 * Finds the value of each key/value pair in
 * the existing string and puts them into the
 * fields of SiteMapAnnotation;
 * 
 */
public class ExistingAnnotation extends SiteMapAnnotation {
	private String annotationStr;
	
	public ExistingAnnotation(String annotationStr) {
		this.annotationStr = annotationStr;
		setParts(annotationStr);
	}
	
	private void setParts(String annotationStr) {
		super
			.setAuthor(getKeyValue("author"))
			.setVersion(getKeyValue("version"))
			.setDate(getKeyValue("date"));
	}
	
	public String getKeyValue(String val) {
		var start = annotationStr.indexOf("\"", annotationStr.indexOf(val));
		var end = annotationStr.indexOf("\"", start + 1);
		
		return annotationStr.substring(start + 1, end);
	}

}
