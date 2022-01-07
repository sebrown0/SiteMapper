/**
 * 
 */
package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.IOException;

import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class AnnotationWriter {
	private BufferedWriter writer;
	private String annotation;
	
	public AnnotationWriter(BufferedWriter writer, SiteMapInfo siteMapInfo) {
		this.writer = writer;
		createAnnotation(siteMapInfo);
	}
	
	private void createAnnotation(SiteMapInfo siteMapInfo) {
		if(siteMapInfo != null) {
			annotation =
					"\t@SiteMap(author=\"" + siteMapInfo.getAuthor() + "\"" + 
					", version=\"" + siteMapInfo.getVersion() + "\"" + 
					", date=\"" + siteMapInfo.getDate() + "\")\n";	
		}else {
			annotation =
					"\t@SiteMap(author=\"\", version=\"\", date=\"\")\n";	
		}
		
	}
	
	public void writeAnnotation() throws IOException {
		writer.write(annotation);
	}
}
