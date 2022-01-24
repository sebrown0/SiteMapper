/**
 * 
 */
package site_mapper.creators;

import java.util.Arrays;
import java.util.List;

import file.annotation.NewAnnotation;
import file.annotation.SiteMapAnnotation;
import file.imports.Import;
import file.imports.NewImport;
import file.variable.ClassVariable;
import file.variable.Variable;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementConstructor;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ComponentWriterJsPanelWithIFrame 
	implements ComponentWriterSetter, ElementConstructor	{
	
	private ElementClass elementClass;
	private SiteMapInfo siteMapInfo;
		
	@Override //ComponentInfo
	public List<Import> getImportNames() {
		return Arrays.asList(
				new NewImport(new UseImport("java.util.List")),
				new NewImport(new UseImport("org.openqa.selenium.By")),
				new NewImport(new UseImport("control_builder.*")),
				new NewImport(new UseImport("site_mapper.annotations.SiteMap")),
				new NewImport(new FindImport("JsPanelWithIFrame", siteMapInfo)),
				new NewImport(new FindImport("CoreData", siteMapInfo)));
	}
	@Override //ComponentInfo
	public List<Variable> getClassVariables() {
		SiteMapAnnotation annotation = new NewAnnotation(siteMapInfo, 1);
		
		return 
			Arrays.asList(  
				new ClassVariable
					.ClassVarFromString("public static final String PANEL_TITLE = \"Employee Details\";")
					.withAnnotation(annotation)
					.build(),
				new ClassVariable
					.ClassVarFromString("public static final String MENU_TITLE = \"Employee Details\";")
					.withAnnotation(annotation)
					.build(),
				new ClassVariable
					.ClassVarFromString("public static final String MENU_PARENT_NAME = \"Employees\";")
					.withAnnotation(annotation)
					.build()
			);		
	}	
	
	@Override //ElementConstructor/ComponentInfo
	public String getClassName() {		
		return elementClass.getClassName();
	}
	@Override //ElementConstructor
	public String getModifier() {
		return "public"; //DEFAULT
	}
	@Override //ElementConstructor
	public String getSuperArgs() {
		return "coreData, PANEL_TITLE";
	}	
	@Override //ElementConstructor
	public String getConstructorArgs() {
		return "CoreData coreData";
	}
	@Override //ElementConstructor
	public List<Object> getConstructorLines() {
		return Arrays.asList("\n\t\tbuildMyControls();");
	}
			
	@Override //ComponentWriterVisitor
	public ComponentWriterSetter setSiteMapInfo(SiteMapInfo siteMapInfo) {
		this.siteMapInfo = siteMapInfo;
		return this;
	}
	@Override //ComponentWriterVisitor
	public ComponentWriterSetter setElementClass(ElementClass elementClass) {
		this.elementClass = elementClass;
		return this;
	}
	
//	@Override //ComponentWriterVisitor
//	public void writeComponents() throws IOException {
//		 	
//	}
	
//	private void writePanelVars() throws IOException {
////		new VariableWriter(fileOut, jsPanel).writePanelVars();;
//	}
//	
//	private void writeConstructor() throws IOException {
//		fileOut.writeNewLine();
//		fileOut.writeAnnotation();
//		new ConstructorWriter(fileOut, this).writeConstuctor();
//	}
//			
//	private void writeBuildControlsFunction() throws IOException {		
//		String func;
//		if(elements != null) {
//			List<ControlDataValues> values = new ArrayList<>();					
//			elements.forEach(e -> {
//				values.add(new ControlDataValues(e));
//			});	
//			
//			ControlDataStringFactory fact = new ControlDataStringFactory(values);
//			try {
//				func = fact.getFunctionBuildMyControls();
//				fileOut.writeNewLines(2);
//				fileOut.writeAnnotation();
//				fileOut.writeValue(func); 	
//			} catch (InvalidArgumentException e1) {
//				// TODO Auto-generated catch block
//				// ** TODO - Add to sitemapper log **
//				System.out.println("ComponentWriterJsPanelWithIFrame.writeBuildControlsFunction -> ** TODO - Add to sitemapper log **"); // TODO - remove or log 	
//				e1.printStackTrace();
//			}
//			
//		}		
//	}
		
}
