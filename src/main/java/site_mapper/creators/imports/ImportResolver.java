/**
 * 
 */
package site_mapper.creators.imports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.helpers.FileFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ImportResolver implements FoundImports {
	private Map<String, String> found = new HashMap<>();
	private List<String> missing = new ArrayList<>();
	private String root;
	private String ignoreFolder;
//	private List<String> required;
			
	private String currentImp;
	
	private final Logger LOGGER = LogManager.getLogger(ImportResolver.class);	
	
	public ImportResolver(String root, String ignoreFolder) {
		this.root = root;
		this.ignoreFolder = ignoreFolder;
//		this.required = required;
	}
	
	public void resolveRequired(List<String> required) {
		LOGGER.info("Attempting to resolve required imports");
		required.forEach(imp -> {			
			currentImp = imp;			
			getImportPath(imp).ifPresentOrElse(
					f -> {
						add(f);
					},
					new Runnable() {						
						@Override
						public void run() {							
							missing.add(currentImp);
							LOGGER.error("Could not find import [" + currentImp + "]");
						}
					});				
		});		
	}
	
	private Optional<String> getImportPath(String imp){
		Optional<String> p = 
				FileFinder.findFilePath(root, imp + ".java", ignoreFolder);
		
		return p;
	}
	
	private void add(String impPath) {
		getSanitisedPath(impPath).ifPresent(p -> {
			if(found.containsKey(currentImp)) {
				LOGGER.error("[" + currentImp + "] already exists in found imports. "
						+ "Check the class files to make sure that they are using the correct version");
			}else {
				found.put(currentImp, p);
			}
		});		
	}
	
	private Optional<String> getSanitisedPath(String impPath) {
		String res = null;
		var pos = impPath.indexOf("\\src\\main\\java\\");
		if(pos > 0) {
			res = impPath.substring(pos+15, impPath.length()-5).replace("\\", ".");
		}else {
			LOGGER.error("[" + currentImp + "] the import path does not contain a "
					+ "[/src/main/java] so is considered an invalid import");
		}
		return Optional.ofNullable(res);
	}

	@Override
	public Map<String, String> getFoundImports() {
		return found;
	}

	public List<String> getMissing() {
		return missing;
	}
	
}
