/**
 * 
 */
package site_mapper.creators.imports;

import java.util.Map;

import org.apache.logging.log4j.LogManager;

import file.helpers.FileFinder;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.0
 * 	Remove "src.main.java." from start of path.
 * 	This is because it could be in the element library
 *  which could be in a different project, workspace etc.
 * @since 1.0
 */
public class FindImport implements ImportType {
	private String importStr;
	private SiteMapInfo siteMapInfo;
	
	public FindImport(String importStr, SiteMapInfo siteMapInfo) {
		this.importStr = importStr;
		this.siteMapInfo = siteMapInfo;
	}

	@Override
	public String getPath(FoundImports foundImports) {
		if(foundImports != null) {
			Map<String, String> resolved = foundImports.getFoundImports();
			if(resolved != null && resolved.containsKey(importStr)) {			
				return "import " + foundImports.getFoundImports().get(importStr) + ";";
			}
		}		
		//Not in resolved imports so try to find it.
		return getPath();
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

	@Override
	public String getImportString() {
		return importStr;
	}
	
	private String getNotFound() {
		return "/* Placeholder for missing import [" + importStr + "] */";
	}

}
