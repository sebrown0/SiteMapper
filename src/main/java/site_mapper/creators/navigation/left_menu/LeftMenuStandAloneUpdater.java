/**
 * 
 */
package site_mapper.creators.navigation.left_menu;

import java.util.List;

import site_mapper.creators.navigation.FactoryUpdater;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add the case statements for stand-alone items 
 * in the left menu, i.e. those that are not in 
 * a sub menu.
 */
public class LeftMenuStandAloneUpdater {
	private final String STAND_ALONE_TOKEN;	
	
	private FactoryUpdater updater;
		
	private List<String> content;
	private List<String> standAlone;
	
	public LeftMenuStandAloneUpdater(FactoryUpdater updater) {
		this.updater = updater;
		this.content = updater.getContent();
		this.standAlone = updater.getStandAlone();
		this.STAND_ALONE_TOKEN = updater.getStandAloneToken();
	}

	public void updateStandAloneItems() {
		if(standAloneShouldBeUpdated()) {
			updater.updateInsertPosition(findStandAloneToken());
			if(thereIsNoStandAloneToken()) {
				updater.updateInsertPosition(findReturnStatement()-1);
				updater.insertToken(STAND_ALONE_TOKEN);
			}else {
				updater.incrementInsertPos();			
			}	
			updater.updateItemsInList(standAlone);
		}		
	}
	
	private boolean standAloneShouldBeUpdated() {
		return standAlone != null && standAlone.size() > 0;
	}
			
	private int findStandAloneToken() {
		int idx = 0;		
		for(String line : content) {
			if(line.contains(STAND_ALONE_TOKEN)) {
				return idx;				
			}
			idx ++;
		}		
		return -1;
	}
	
	private boolean thereIsNoStandAloneToken() {
		return updater.getInsertPos() == -1; 
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
	
}
