/**
 * 
 */
package site_mapper.creators.component_writer;

import java.util.ArrayList;
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
import site_mapper.creators.imports.FoundImports;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.imports.NewImportResolver;
import site_mapper.creators.imports.RequiredImports;
import site_mapper.creators.imports.UseImport;
import site_mapper.elements.DefaultNoArgsConstructor;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementConstructor;
import site_mapper.jaxb.menu_items.TypeAttributes;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Write all the components of a class file
 * for JsPanelWithIFrame.java.
 */
public class ComponentWriterJsPanelWithIFrame implements 
	RequiredImports, ComponentWriterSetter, 
	ElementConstructor, DefaultNoArgsConstructor {
	
	private ElementClass elementClass;
	@SuppressWarnings("unused")
	private SiteMapInfo siteMapInfo;
	private ClassVariable panelTitle;
	private ClassVariable menuTitle;
	private ClassVariable menuParentName;	
	private List<Import> imports;
	private SiteMapAnnotation annotation;

	private ImportMatcher impMatcher;	
	private FoundImports foundImports;

	@Override //ComponentWriter
	public void addImport(Import imp) {
		boolean addIt = true;
		String impStr = imp.getImportString();
		
		getImportNames();
		for(Import i: imports) {			
			if(i.getImportString().equals(impStr)) {
				addIt = false;
				break;
			}
		}
		if(addIt)	imports.add(imp);
	}
		
	@Override //ComponentInfo
	public List<Import> getImportNames() {
		if(imports==null) {
			imports = new ArrayList<>();
			imports.add(new NewImport(new UseImport("static org.junit.jupiter.api.Assertions.assertTrue"), foundImports));
			imports.add(new NewImport(new UseImport("static org.junit.jupiter.api.Assertions.fail"), foundImports));
			imports.add(new NewImport(new UseImport("java.util.List"), foundImports));
			imports.add(new NewImport(new UseImport("java.util.Arrays"), foundImports));
			imports.add(new NewImport(new UseImport("org.openqa.selenium.By"), foundImports));
			imports.add(new NewImport(new UseImport("control_builder.*"), foundImports));
			imports.add(new NewImport(new UseImport("site_mapper.annotations.SiteMap"), foundImports));
			imports.add(new NewImport(new UseImport("org.junit.jupiter.api.DynamicTest"), foundImports));
			resolveImports();
		}
		return imports;
	}
	
	@Override //RequiredImports
	public void updateWithMatched(String imp) {
		imports.add(new NewImport(new UseImport(imp), impMatcher.getFoundImports()));
	}

	@Override //RequiredImports
	public List<String> getRequiredImports() {
		return
			Arrays.asList(
				"TestControl", "ControlGetter", "ControlGetterGroup", 
				"JsPanelWithIFrame", "ControlData", "CoreData");
	}
	
	private void resolveImports() {
		impMatcher.matchImports(new NewImportResolver(this));		
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
	public ComponentWriterSetter setImportMatcher(ImportMatcher impMatcher) {
		this.impMatcher = impMatcher;
		this.foundImports = impMatcher.getFoundImports();
		return this;
	}	
	
	@Override //ComponentWriterSetter
	public ComponentWriterSetter setSiteMapInfo(SiteMapInfo siteMapInfo) {
		annotation = new NewAnnotation(siteMapInfo, 1);
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

	@Override //DefaultNoArgsConstructor
	public String getConstructor() {
		String res = String.format("%s\n\tpublic %s(){}", annotation.toString(), elementClass.getClassName());
		return res;
	}
		
}
