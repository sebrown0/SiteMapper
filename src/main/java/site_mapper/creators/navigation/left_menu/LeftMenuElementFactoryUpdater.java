package site_mapper.creators.navigation.left_menu;

import static file.helpers.FileFinder.findFilePath;
import static utils.StringUtils.asPascalCase;
import static utils.StringUtils.removeSpacesAndAsPascalCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import site_mapper.creators.navigation.FactoryInfo;
import site_mapper.creators.navigation.FactoryUpdater;
import utils.FileReader;
import utils.FileWriter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Update the [MODULE]LeftMenuElementFactory with new 
 * parents and stand-alone items.
 * 
 * Parents are items that have a sub menu.
 * Stand-alone items are not part of a sub menu,
 * i.e. clicking the item takes you to a new page.
 * 
 * A new case statement is added for each new item.
 * 
 * To find a parent and add a child item to it we
 * look for a token /* PARENT NAME */            /*
 * 
 * The Parent Name comes from the MenuItem's package.
 * In the XML this should be in the format [a_package]
 * as this is the actual name of the package.
 * 
 * We convert it to Pascal Case with underscores 
 * replaced by spaces.
 * --
 * 
 * To find a stand-alone item we look for 
 * the token /* STAND ALONE */            /*
 * 
 * All new stand-alone items are placed below
 * this token.
 * 
 * NOTES
 * -----
 * 1. This does not create the file from scratch,
 * 		so it must exist in it's basic form.
 * 2. The stand-alone token must be above the 
 * 		default case statement to keep the formatting.
 * 
 */

public class LeftMenuElementFactoryUpdater implements FactoryUpdater {
	private static final String STAND_ALONE_TOKEN = "/* Stand Alone */";	
	
	private List<String> content;	
	private int numLinesInContent;
	private int insertPos;
	private Optional<String> filePath;
	
	private FactoryInfo info;
	private Map<String, List<String>> parents = new HashMap<>();
	private List<String> standAlone = new ArrayList<>();
		
	public LeftMenuElementFactoryUpdater(
		FactoryInfo info, 
		Map<String, List<String>> parents,
		List<String> standAlone) {
		
		this.info = info;
		this.parents = parents;
		this.standAlone = standAlone;		
	}
	
	// Only returns the content so we can use it in UTs. 
	public List<String> updateFactoryContents() {
		setFilePath();
		filePath.ifPresent(fp -> {
			setContent();
			update();	
		});
		return content;		
	}	

	private void setContent() {
		content = FileReader.getFileAsList(filePath);
		if(content != null) {
			numLinesInContent = content.size()-1;
		}
	}	
	
	private void setFilePath(){
		filePath = findFilePath(
				info.getRoot(), 
				getFactoryName());		
	}
	
	@Override
	public String getFactoryName() {
		return 
			asPascalCase(info.getModule()) + 
			"LeftMenuElementFactory.java";
	}
			
	private void update() {
		updateParents();	
		updateStandAloneItems();
		updateImports();		
	}
	
	private void updateParents() {
		new LeftMenuParentUpdater(this).updateParents();
	}
	
	private void updateStandAloneItems() {
		new LeftMenuStandAloneUpdater(this).updateStandAloneItems();
	}
	
	private void updateImports() {
		new LeftMenuImportUpdater(this).updateImports();
	}
			
	public void writeUpdatedContentToFile() {
		FileWriter.writeFile(this, filePath.get());
	}
	
	@Override
	public void updateItemsInList(List<String> items) {
		for(String item : items) {
			var itemName = removeSpacesAndAsPascalCase(item);	
			if(locationOfItemInCurrList(insertPos, itemName) == -1) {							
				insertItem(itemName);
			}	
		}							
	}
	
	private int locationOfItemInCurrList(int start, String itemName) {
		int res = -1;
		for(int idx = start; idx < numLinesInContent; idx++) {
			var line = content.get(idx);
			if(isEndOfSearch(line)) {
				break;
			}else if(line.contains(itemName)) {
				res = idx;
				break;
			}
		}
		return res;
	}
	
	private boolean isEndOfSearch(String line) {
		return
			line.contains("/*") ||
			line.contains(STAND_ALONE_TOKEN) ||
			line.contains("return");
	}
	
	private void insertItem(String item) {
		String line = 
			String.format(
				"\t\t\tcase %s.MENU_TITLE:\n" +
				"\t\t\t\telement = new %s(coreData);\n" +
				"\t\t\t\tbreak;", item, item);		
		addLine(line);
	}
	
	@Override
	public void insertToken(String token) {		
		String line = String.format("\n\t\t\t%s", token);		
		addLine(line);
	}	
	@Override
	public void addLine(String line) {
		content.add(insertPos, line);
		insertPos++;
		numLinesInContent++;		
	}
	@Override
	public FactoryInfo getFactoryInfo() {
		return info;
	}
	@Override
	public Map<String, List<String>> getParents() {
		return parents;
	}
	@Override
	public List<String> getStandAlone() {
		return standAlone;
	}
	@Override
	public void updateInsertPosition(int pos) {
		insertPos = pos;
	}
	@Override
	public List<String> getContent() {
		return content;
	}
	@Override
	public int getInsertPos() {
		return insertPos;
	}
	@Override
	public String getStandAloneToken() {
		return STAND_ALONE_TOKEN;
	}
	@Override
	public void incrementInsertPos() {
		insertPos++;
	}	
	
	@Override
	public String toString() {
		String res = "";
		for(String ln : content) {
			res += ln + "\n";
		}
		return res;
	}
	
}