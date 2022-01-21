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
 * @version 1.0
 * 	Remove "src.main.java." from start of path.
 * 	This is because it could be in the element library
 *  which could be in a diiferent project, workspace etc.
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
				.findPathWithoutRootAndExtension(siteMapInfo.getElementLibrary(), importStr + ".java")
				.replaceAll("\\\\", ".")
				.replace("src.main.java.", "");								
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
