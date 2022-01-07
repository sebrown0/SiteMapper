/**
 * 
 */
package site_mapper.creators;

import org.apache.logging.log4j.LogManager;

import site_mapper.jaxb.pom.SiteMapInfo;
import utils.FileFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class FindImport implements ImportType{
	private String importStr;
	private SiteMapInfo siteMapInfo;
	
	public FindImport(String importStr, SiteMapInfo siteMapInfo) {
		this.importStr = importStr;
		this.siteMapInfo = siteMapInfo;
	}

	@Override
	public String getPath() {
		if(siteMapInfo != null) {
			String importPath = FileFinder
				.findPathWithoutRootAndExtension(siteMapInfo.getRootDir(), importStr + ".java")
				.replaceAll("\\\\", "."); 

			return "import " + importPath + ";";					
		}else {
			LogManager.getLogger(FindImport.class).error("Could not get import for [" + importStr + "]");;
			return "";
		}
		
	}

}
