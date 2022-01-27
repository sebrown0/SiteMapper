/**
 * 
 */
package app;

import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface PomMapperVisitor {
	void setSiteMapInfo(SiteMapInfo info);
}
