/**
 * 
 */
package site_mapper.creators.control_type;

import logging.AppLogger;
import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlTypeFactory {
	
	public static ControlType getControlType(ElementCreation el) {
		ControlType ct = null;
		if(el != null) {
			String key  = el.getElementType();
			switch (key) {
				case "Button", "button" -> ct = new ControlTypeButton(el);
				case "Label", "label" -> ct = new ControlTypeLabel(el);
				case "TextOut" -> ct = new ControlTypeTextOut(el);
				case "TextSelect" -> ct = new ControlTypeTextSelect(el);
				case "ComboWriteAndSelect" -> ct = new ControlTypeComboWriteAndSelect(el);
				case "ComboSelectOnly" -> ct = new ControlTypeComboSelectOnly(el);
				case "DropDownCombo" -> ct = new ControlTypeDropDownCombo(el);
				
				default -> defaultAction(key);
			}	
		}		
		return ct;
	}
	
	private static void defaultAction(String key) {
		AppLogger.logError("No control type for [" + key +"] found", ControlTypeFactory.class);
		throw new IllegalArgumentException("Unexpected value: " + key);
	}
	
}
