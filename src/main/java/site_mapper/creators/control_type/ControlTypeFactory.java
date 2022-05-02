/**
 * 
 */
package site_mapper.creators.control_type;

import org.apache.logging.log4j.LogManager;

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
				case "ComboWriteAndSelect" -> ct = new ControlTypeComboWriteAndSelect(el);
				
				default -> {
					LogManager.getLogger().error("No control type for [" + key +"] found");
					throw new IllegalArgumentException("Unexpected value: " + key);
				}
			}	
		}		
		return ct;
	}
	
}
