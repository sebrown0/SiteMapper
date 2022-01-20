/**
 * 
 */
package file.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class LineTabs {
	private final Pattern PATTERN = Pattern.compile("\t");
	private Matcher MATCHER ;
	private int numTabs;
	
	public LineTabs() {
	}
	
	public LineTabs(String line) {
		MATCHER = PATTERN.matcher(line);
		setNumTabs();
	}
	
	public void setNumTabs() {		
		while(MATCHER.find()) {
			numTabs++;
		}
	}
	public int getNumTabs() {
		return numTabs;
	}
	public String getLineWithTabs(String line) {
		return getTabStr(this.numTabs) + line;
	}
	public static String getLineWithTabs(int numTabs, String line) {
		return getTabStr(numTabs) + line;
	}
	public static String getTabStr(int numTabs) {
		String tabs = "";
		while(--numTabs >= 0) {
			tabs += "\t";
		}
		return tabs;
	}
}
