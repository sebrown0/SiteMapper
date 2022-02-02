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
				new NewImport(new UseImport("static org.junit.jupiter.api.Assertions.assertTrue")),
				new NewImport(new UseImport("static org.junit.jupiter.api.Assertions.fail")),
				
				new NewImport(new UseImport("java.util.List")),
				new NewImport(new UseImport("org.openqa.selenium.By")),
				new NewImport(new UseImport("control_builder.*")),
				new NewImport(new UseImport("site_mapper.annotations.SiteMap")),				
				new NewImport(new UseImport("org.junit.jupiter.api.DynamicTest")),				
				new NewImport(new FindImport("TestControl", siteMapInfo)),				
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
		
}
