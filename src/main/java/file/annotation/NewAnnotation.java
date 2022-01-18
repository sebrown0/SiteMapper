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
		super
			.setAuthor(info.getAuthor())
			.setVersion(info.getVersion())
			.setDate(info.getDate());
	}	
		
}
