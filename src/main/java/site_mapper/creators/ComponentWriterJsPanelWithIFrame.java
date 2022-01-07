/**
 * 
 */
package site_mapper.creators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.InvalidArgumentException;
import site_mapper.elements.Element;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.menu_items.JsPanelWithIFrame;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ComponentWriterJsPanelWithIFrame implements ComponentWriterVisitor{
	private ClassWriterActions fileOut;
	private String className;
	private JsPanelWithIFrame jsPanel;
	private List<Element> elements;
	private SiteMapInfo siteMapInfo;
	
	@Override //ComponentWriter
	public List<ImportType> getImportNames() {
		return Arrays.asList(
				new UseImport("java.util.List"),
				new UseImport("org.openqa.selenium.By"),
				new UseImport("control_builder.*"),
				new UseImport("site_mapper.annotations.SiteMap"),

				new FindImport("JsPanelWithIFrame", siteMapInfo),
				new FindImport("CoreData", siteMapInfo));
	}
	@Override //ComponentWriter
	public List<String> getSuperArgs() {
		return Arrays.asList("coreData", "PANEL_TITLE");
	}	
	@Override //ComponentWriter
	public List<String> getConstructorArgs() {
		return Arrays.asList("CoreData coreData");
	}
	@Override
	public List<String> getConstructorLines() {
		return Arrays.asList("\n\t\tbuildMyControls();");
	}
	@Override //ComponentWriter
	public String getClassName() {		
		return className;
	}
	
	@Override //ComponentWriterVisitor
	public ComponentWriterVisitor setFileOutWriter(ClassWriterActions fileOut) {
		this.fileOut = fileOut;
		return this;
	}
	@Override //ComponentWriterVisitor
	public ComponentWriterVisitor setSiteMapInfo(SiteMapInfo siteMapInfo) {
		this.siteMapInfo = siteMapInfo;
		return this;
	}
	@Override //ComponentWriterVisitor
	public ComponentWriterVisitor setElementClass(ElementClass elementClass) {
		this.jsPanel = (JsPanelWithIFrame) elementClass.getMenuItemType().getJs();
		this.elements = elementClass.getElements();
		this.className = elementClass.getClassName();
		return this;
	}
	
	@Override //ComponentWriterVisitor
	public void writeComponents() throws IOException {
		writePanelVars();		
		writeConstructor();
		writeBuildControlsFunction();
	}
	
	public void writePanelVars() throws IOException {
		new VariableWriter(fileOut, jsPanel).writePanelVars();;
	}
	
	private void writeConstructor() throws IOException {
		fileOut.writeNewLine();
		fileOut.writeAnnotation();
		new ConstructorWriter(fileOut, this).writeConstuctor();
	}
			
	private void writeBuildControlsFunction() throws IOException {		
		String func;
		if(elements != null) {
			List<ControlDataValues> values = new ArrayList<>();					
			elements.forEach(e -> {
				values.add(new ControlDataValues(e));
			});	
			
			ControlDataStringFactory fact = new ControlDataStringFactory(values);
			try {
				func = fact.getFunctionBuildMyControls();
				fileOut.writeNewLines(2);
				fileOut.writeAnnotation();
				fileOut.writeValue(func); 	
			} catch (InvalidArgumentException e1) {
				// TODO Auto-generated catch block
				// ** TODO - Add to sitemapper log **
				System.out.println("ComponentWriterJsPanelWithIFrame.writeBuildControlsFunction -> ** TODO - Add to sitemapper log **"); // TODO - remove or log 	
				e1.printStackTrace();
			}
			
		}		
	}
		
}
