/**
 * 
 */
package site_mapper.creators;

import java.io.IOException;

import site_mapper.jaxb.menu_items.JsPanelWithIFrame;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class VariableWriter {
	private ClassWriterActions fileOut;
	private JsPanelWithIFrame jsPanel; //TODO - JsPanelWithIframe should extend JsPanel.
	
	public VariableWriter(ClassWriterActions fileOut, JsPanelWithIFrame jsPanel) {
		this.fileOut = fileOut;
		this.jsPanel = jsPanel;
	}
	
	public void writePanelVars() throws IOException {
		writeStaticString("PANEL_TITLE", jsPanel.getPanelTitle());
		writeStaticString("MENU_TITLE", jsPanel.getMenuTitle());
		writeStaticString("MENU_PARENT_NAME", jsPanel.getMenuParentName());
	}
	
	private void writeStaticString(String name, String val) throws IOException {
		fileOut.writeAnnotation();
		fileOut.writeLine(String.format("\tpublic static final String %s = \"%s\";", name, val));
	}
	
}
