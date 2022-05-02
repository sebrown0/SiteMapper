/**
 * 
 */
package site_mapper.creators.navigation.left_menu;

import static logging.AppLogger.logInfo;
import static utils.text_utils.StringUtils.replaceUnderScoresWithSpaceAndAsPascalCase;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import site_mapper.creators.navigation.FactoryUpdater;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add the case statements for parents in the left menu.
 * 
 * Find the parent by the token "/* PARENT NAME */ /*"
 * If the token doesn't exist, i.e. the parent is not already
 * part of the menu, add it.
 * 
 */
public class LeftMenuParentUpdater {
	private FactoryUpdater updater;
	
	private Map<String, List<String>> parents;
	private List<String> content;
	private String currentParentToken;
	
	public LeftMenuParentUpdater(FactoryUpdater updater) {		
		this.updater = updater;
		this.parents = updater.getParents();
		this.content = updater.getContent();
	}

	public void updateParents() {
		if(parents != null) {
			writeInitialLog();			
			for(Map.Entry<String, List<String>> e : parents.entrySet()){
				var parent = e.getKey();
				if(parentShouldBeUpdate(parent)) {
					setCurrentParentToken(parent);
					findInsertPosInParent();
					if(parentExistsInContent()) {
						updater.updateInsertPosition(updater.getInsertPos()+1);
						updater.updateItemsInList(e.getValue());
					}else {
						addNewParentAndChildren(e);
					}					
				}			 	
			}	
		}else {
			logInfo("[" + updater.getFactoryName() + "] has no new parents", getClass());
		}
	}
	
	private void writeInitialLog() {
		logInfo("Using token of format [/* PARENT NAME */]", getClass());
		logInfo(
			String.format(
					"Updating [%s] with parents", 
					updater.getFactoryName()), getClass());
	}
	
	private boolean parentShouldBeUpdate(String parent) {
		return parents.containsKey(parent);
	}
	
	private void setCurrentParentToken(String parent) {
		currentParentToken = 
				"/* " + 
				replaceUnderScoresWithSpaceAndAsPascalCase(parent) + 
				" */";
	}
	
	private void addNewParentAndChildren(Entry<String, List<String>> e) {
		int end = findPosForNewParent();		
		if(end > 1) {
			updater.updateInsertPosition(end-1);
			updater.insertToken(currentParentToken);
			updater.updateItemsInList(e.getValue());
		}
	}
	
	private int findPosForNewParent() {
		int end = findStandAloneToken();
		if(end == -1) {
			end = findReturnStatement();	
		}
		return end;
	}
	
	private int findStandAloneToken() {
		int idx = 0;		
		for(String line : content) {
			if(line.contains(updater.getStandAloneToken())) {
				return idx;				
			}
			idx ++;
		}		
		return -1;
	}
	
	private int findReturnStatement() {
		int idx = 0;		
		for(String line : content) {
			if(line.contains("return element;")) {
				return idx;				
			}
			idx ++;
		}		
		return -1;
	}
	
	private void findInsertPosInParent() {
		updater.updateInsertPosition(findLoc());
	}
	
	private boolean parentExistsInContent() {
		return updater.getInsertPos() > 0;
	}

	private int findLoc() {
		int res = -1, idx = 0;
		for(String ln : content) {
			if(tokenFound(ln)) {
				res = idx;
				break;
			}
			idx++;
		}
		return res;
	}

	private boolean tokenFound(String ln) {		
		if(ln.contains(currentParentToken)){
			return true;
		}else {
			
		}		
		return false;
	}
	
}
