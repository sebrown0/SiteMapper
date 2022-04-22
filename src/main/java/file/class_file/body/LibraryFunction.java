/**
 * 
 */
package file.class_file.body;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.class_file.ImportAppender;
import file.class_file.body.ClassBody.NewClassBody;
import file.helpers.FileFinder;
import file.imports.Import;
import file.imports.NewImport;
import site_mapper.creators.imports.FindImport;
import site_mapper.creators.imports.FoundImports;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the 'library' object that is an optional attribute of a MenuItem.
 * Add it to the 'POM' as a function that returns a new object of 
 * the library type.
 * 
 */
public class LibraryFunction {
	private SiteMapInfo info;
	private ElementClass clazz;
		
	private final Logger LOGGER = LogManager.getLogger(NewClassBody.class);
	
	public LibraryFunction(SiteMapInfo info, ElementClass clazz) {
		this.info = info;
		this.clazz = clazz;
	}

	public String getLibraryFunction(ImportAppender importAppender, ImportMatcher impMatcher) {		
		String libName = clazz.getLibrary();
		
		if(libName != null) {
			if(FileFinder.fileExists(info.getRootDir(), libName + ".java")) {
				appendImport(importAppender, impMatcher.getFoundImports(), libName);
				return goodLibrary(libName); 	
			}else {
				logError(libName);			
				return badLibrary(libName);
			}
		}else {
			logInfo();			
			return noLibrary();
		}	
	}
	
	private void logError(String libName) {
		LOGGER.error(
			String.format(
					"[%s] has library [%s] but it was not found on path [%s]", 
					clazz.getClassName(), libName, info.getRootDir()));
	}
	private void logInfo() {
		LOGGER.info(
			String.format(
					"[%s] has no library", clazz.getClassName()));
	}
	
	private void appendImport(ImportAppender importAppender, FoundImports foundImports, String libName) {
		Import imp = new NewImport(new FindImport(libName, info), foundImports);
		importAppender.appendImport(imp);
	}
	
	private String goodLibrary(String libName) {
		String res = 
			String.format(
				"\tpublic %s get%s() {\n" +
				"\t\treturn new %s(coreData);\r\n" +
				"\t}", libName, libName, libName);
		
		return res;
	}
	
	private String badLibrary(String libName) {
		String res = 
			String.format(
				"/* Placeholder for missing library [%s]*/", libName);
		
		return res;
	}
	
	private String noLibrary() {		
		return "";
	}
	
}
