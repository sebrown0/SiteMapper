/**
 * 
 */
package site_mapper.creators;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import file.annotation.NewAnnotation;
import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;
import file.imports.Import;
import file.imports.NewImport;
import file.variable.ClassVariable;
import file.variable.Variable;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementConstructor;
import site_mapper.jaxb.menu_items.TypeAttributes;
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

	private ClassVariable panelTitle;
	private ClassVariable menuTitle;
	private ClassVariable menuParentName;
	
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
		return	Arrays.asList(panelTitle,	menuTitle, menuParentName);		
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
			
	@Override //ComponentWriterSetter
	public ComponentWriterSetter setSiteMapInfo(SiteMapInfo siteMapInfo) {
		this.siteMapInfo = siteMapInfo;
		return this;
	}
	@Override //ComponentWriterSetter
	public ComponentWriterSetter setElementClass(ElementClass elementClass) {
		this.elementClass = elementClass;
		setTypeAttributes();
		return this;
	}
	
	public void setTypeAttributes() {
		SiteMapAnnotation annotation = new NewAnnotation(siteMapInfo, 1);
		TypeAttributes typeAttributes = elementClass.getTypeAttributes();

		if(typeAttributes != null) {
			panelTitle = (ClassVariable) new ClassVariable
					.ClassVarFromString("public static final String PANEL_TITLE = \"" + typeAttributes.getPanelTitle() + "\";")
					.withAnnotation(annotation)
					.build();	
		}else {
			LogManager.getLogger(
					this.getClass()).error(
							"Cannot set [PANEL_TITLE] as TypeAttributes is NULL");
			
			panelTitle = (ClassVariable) new ClassVariable
					.ClassVarFromString("/** - MISSING PANEL_TITLE - **/")
					.withAnnotation(annotation)
					.build();
		}
		
		menuTitle = (ClassVariable) new ClassVariable				
				.ClassVarFromString("public static final String MENU_TITLE = \"" + elementClass.getName() + "\";")
				.withAnnotation(annotation)
				.build();	
		
		menuParentName = (ClassVariable) new ClassVariable				
				.ClassVarFromString(
						"public static final String MENU_PARENT_NAME = \"" + 
						Formatter.capitaliseFirstChar(elementClass.getPackage()) + "\";")
				.withAnnotation(annotation)
				.build();	
	}
		
}
