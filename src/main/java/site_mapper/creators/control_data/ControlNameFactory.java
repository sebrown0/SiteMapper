/**
 * 
 */
package site_mapper.creators.control_data;

import org.apache.logging.log4j.LogManager;

import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlNameFactory {
	public static String getNameWithAcronym(String cntrlType, String name) {
		String acronym = "";
		
		if(cntrlType.equalsIgnoreCase("Button")) {
			acronym = "btn";
		}else if(cntrlType.equalsIgnoreCase("Label")) {
			acronym = "lbl";
		}else if(cntrlType.equalsIgnoreCase("Grid")) {
			acronym = "grd";
		}else if(cntrlType.equalsIgnoreCase("TextOut")) {
			acronym = "txo";
		}else if(cntrlType.equalsIgnoreCase("TextSelect")) {
			acronym = "txs";
		}else if(cntrlType.equalsIgnoreCase("ComboSelectOnly")) {
			acronym = "cso";
		}else if(cntrlType.equalsIgnoreCase("ComboWriteAndSelect")) {
			acronym = "cws";
		}else if(cntrlType.equalsIgnoreCase("DropdownCombo")) {
			acronym = "cdd";
		}
		else {
			LogManager
				.getLogger()
					.error("Control type [" + cntrlType + "] has no associated acronym");
		}
		
		return acronym + StringUtils.capitaliseFirstChar(name);
	}
}
