/**
 * 
 */
package site_mapper.creators.comment;

import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Comments {
	public static String getParentPackageComments(SiteMapInfo siteMapInfo) {
		return 
				"/**\n* Parent Package for all Generated Packages." + 
				"\n* ------------------------------------------" +
				getFields(siteMapInfo) + 
				"\n*/\n";
	}
	
	public static String getPackageComments(SiteMapInfo siteMapInfo) {
		return 
				"/**\n* Generated Package." + 
				"\n* ------------------" +
				getFields(siteMapInfo) + 
				"\n*/\n";
	}
	
	public static String getClassComments(SiteMapInfo siteMapInfo) {
		return 
				"/**\n* Generated Class." + 
				"\n* ----------------" +
				getFields(siteMapInfo) + 
				"\n*/\n";
	}
	
	private static String getFields(SiteMapInfo siteMapInfo) {
		if(siteMapInfo != null) {
			return 
					"\n* Source:  " + siteMapInfo.getXmlSource() +
					"\n* Author:  " +	siteMapInfo.getAuthor() + 
					"\n* Version: " + siteMapInfo.getVersion() +
					"\n* Created: " + siteMapInfo.getTimeStamp();	
		}else {
			return 
					"\n* Source:  unknown" +
					"\n* Author:  unknown" + 
					"\n* Version: unknown" +
					"\n* Created: unknown"; 
		}
		
	}
}
