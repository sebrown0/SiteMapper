/**
 * 
 */
package site_mapper.creators.navigation;

import java.util.List;
import java.util.Map;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface FactoryUpdater {
	FactoryInfo getFactoryInfo();
	Map<String, List<String>> getParents();
	List<String> getStandAlone();
	List<String> getContent();
	int getInsertPos();
	String getStandAloneToken();
	String getFactoryName();
	
	void incrementInsertPos();
	void updateItemsInList(List<String> items);
	void insertToken(String token);
	void updateInsertPosition(int pos);
	void addLine(String line);
	
}
