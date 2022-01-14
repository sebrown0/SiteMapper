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
 */
public class ExistingAnnotation extends SiteMapAnnotation {
	private String annotationStr;

	public ExistingAnnotation(String annotationStr) {
		this.annotationStr = annotationStr;
		/*
		 * PARSE THE STRING TO GET THE ELEMENTS.
		 */
	}
	
//	public ExistingAnnotation(String author, String version, String date) {
//		super(author, version, date);
//	}	
		
}
