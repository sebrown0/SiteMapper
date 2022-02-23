/**
 * 
 */
package file.annotation;

import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for existing annotation @SiteMap
 */
public class NewAnnotation extends SiteMapAnnotation {

	public NewAnnotation(SiteMapInfo info) {
		super.setIndent(0);

		super
			.setAuthor(info.getAuthor())
			.setVersion(info.getVersion())
			.setDate(info.getDate());
	}
	
	public NewAnnotation(SiteMapInfo info, int numTabs) {
		super.setIndent(numTabs);

		super
			.setAuthor(info.getAuthor())
			.setVersion(info.getVersion())
			.setDate(info.getDate());
	}
		
}
