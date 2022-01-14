/**
 * 
 */
package site_mapper.creators;

import org.apache.logging.log4j.LogManager;

import file.FileFinder;
import site_mapper.jaxb.pom.SiteMapInfo;

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
		String importPath = "";
		if(siteMapInfo != null) {
			importPath = FileFinder
				.findPathWithoutRootAndExtension(siteMapInfo.getRootDir(), importStr + ".java")
				.replaceAll("\\\\", ".");								
		}else {
			LogManager
				.getLogger(FindImport.class)
				.error("Could not get import for [" + importStr + "]");;			
		}
		return (importPath != null && importPath.length()>1) ? 
				"import " + importPath + ";" : 
					getNotFound();
	}

	private String getNotFound() {
		return "/* Placeholder for missing import [" + importStr + "] */";
	}
}
