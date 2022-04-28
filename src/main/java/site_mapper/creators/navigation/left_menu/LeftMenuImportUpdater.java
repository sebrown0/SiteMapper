/**
 * 
 */
package site_mapper.creators.navigation.left_menu;

import static utils.StringUtils.removeSpacesAndAsPascalCase;
import static utils.StringUtils.replaceFwdSlashes;
import static utils.StringUtils.replaceSpacesWithUnderScoreAndInLower;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import site_mapper.creators.navigation.FactoryUpdater;
import utils.ImportHelper;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Update the imports required for [MODULE]LeftMenuElementFactory.
 * 
 */
public class LeftMenuImportUpdater {
	private final String IMPORT_PRE_AMBLE;
	
	private List<String> existingImports = new ArrayList<>();

	private FactoryUpdater updater;
	
	public LeftMenuImportUpdater(FactoryUpdater updater) {
		this.updater = updater;		

		IMPORT_PRE_AMBLE = 
			String.format(
				"import %s.%s.left_menu", 
				replaceFwdSlashes(
						updater.getFactoryInfo().getParentPackage(), "."), 
						updater.getFactoryInfo().getModule());
	}

	public void updateImports() {		
		getExistingImports();
		updateParents();
		updateStandAlone();
	}
	
	private void getExistingImports() {
		int idx = 0;
		
		for(String line : updater.getContent()) {
			idx++;
			
			if(line.startsWith("import")) {
				existingImports.add(ImportHelper.getClassFromImportStr(line));
				updater.updateInsertPosition(idx);
			}else if(line.startsWith("public class")) {
				break;
			}
		}
	}

	private void updateParents() {
		Map<String, List<String>> parents = updater.getParents();
		if(parents != null) {
			for(Map.Entry<String, List<String>> e : parents.entrySet()) {
				String prntName = replaceSpacesWithUnderScoreAndInLower(e.getKey());
				for(String item : e.getValue()) {				
					addImportForParent(item, prntName);	
				}
			}	
		}		
	}
	
	private void addImportForParent(String item, String prnt) {
		String itemName = removeSpacesAndAsPascalCase(item);
		addImport(itemName, String.format("%s.%s", prnt, itemName));	
	}
	
	private void updateStandAlone() {
		List<String> standAlone = updater.getStandAlone();
		if(standAlone != null) {
			for(String item : standAlone) {			
				addImportForStandAlone(item);
			}	
		}		
	}
	
	private void addImportForStandAlone(String item) {		
		String itemName = removeSpacesAndAsPascalCase(item);
		addImport(itemName, itemName);
	}
		
	private void addImport(String itemName, String imp) {
		if(!existingImports.contains(itemName)) {
			existingImports.add(itemName);
			updater.addLine(String.format("%s.%s;", IMPORT_PRE_AMBLE, imp));	
		}			
	}
	
}
