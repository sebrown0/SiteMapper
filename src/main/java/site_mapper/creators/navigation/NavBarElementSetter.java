package site_mapper.creators.navigation;

import java.util.Arrays;
import java.util.List;

import file.imports.Import;
import file.imports.NewImport;
import site_mapper.creators.imports.FindImport;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.imports.UseImport;
import site_mapper.jaxb.menu_items.MenuItem;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Set the elements that belong to the NavBar,
 * so that they can be retrieved using the element's
 * key or by getting the quick link.
 */
public class NavBarElementSetter extends NavElementCreator {
	
	public NavBarElementSetter(ImportMatcher impMatcher) {
		super(impMatcher);
		
	}
		
	@Override
	public String toString() {
		String res = 
			String.format(
				"%s\n\n%s\n%s\n%s\n%s\n\n%s\n\n%s\n%s\n}", 
				getPackageDeclaration(),
				getImports(),
				getComment(),
				getDeclaration(),
				getVars(),
				getConstructor(),
				getSetElements(),
				getOverriddenFunctions());
			
		return res;
	}
	
	@Override //NavElementAdder
	public void addElement(MenuItem item) {		
		addImport(item);
		addElementToList(item);
		writeNavBarElement(item);
	}
	
	protected void addImport(MenuItem item) {
		String imp = "NavBar"+item.getClassName();
		imports.add(new NewImport(new FindImport(imp, info), impMatcher.getFoundImports()));			
	}
		
	private void addElementToList(MenuItem item) {		
		var itemNameAsNav = "NavBar" + item.getClassName(); 
		elements.add(
				String.format(
						"{\n\t\t\t%s.ORIGINAL_NAME, new %s(coreData)},", 
						itemNameAsNav, itemNameAsNav));
	}

	private void writeNavBarElement(MenuItem item) {
		TopRightNavElementCreator trElmCreator = new TopRightNavElementCreator(impMatcher, item);
		trElmCreator.setPackageHierarchy(ph).setSiteMapInfo(info);
		trElmCreator.writeNavBarElement();
	}
	
	@Override
	protected void setClassName() {
		className = 
			String.format("NavBar%sElements", StringUtils.asPascalCase(modName));
	}
	
	@Override //RequiredImports
	public void updateWithMatched(String imp) {
		imports.add(new NewImport(new UseImport(imp), impMatcher.getFoundImports()));
	}

	@Override //RequiredImports
	public List<String> getRequiredImports() {
		return
			Arrays.asList(
				"NavBarElement", "NavBarElementStrategy", "QuickLinks", 
				getQuickLinkForModule(), "CoreData");
	}
	
	private String getQuickLinkForModule() {
		return "QuickLinks" +  StringUtils.asPascalCase(modName);
	}
	
	@Override
	protected String getImports() {		
		if(requiredImportsAdded==false) {
			requiredImportsAdded=true;
			imports.add(new NewImport(new UseImport("java.util.Map"), foundImports));
			imports.add(new NewImport(new UseImport("java.util.stream.Collectors"), foundImports));			
			imports.add(new NewImport(new UseImport("java.util.stream.Stream"), foundImports));			
			imports.add(new NewImport(new UseImport("org.openqa.selenium.WebDriver"), foundImports));
			resolveImports();
		}
		
		String res = "";
		for (Import imp : imports) {
			res += imp.toString();
		}
		return res;
	}
	
	@Override
	protected String getOverriddenFunctions() {
		return 
			"\n\t@Override\r\n" +
			"\tpublic Map<String, NavBarElement> getElements() {\n" +
			"\t\treturn elements;\n" +
				"\t}\n\n" +

			"\t@Override\n" +
			"\tpublic QuickLinks getQuickLinks() {\n" +
			"\t\treturn new " + getQuickLinkForModule() + "(driver);\n" +
			"\t}";
	}
	
	@Override
	protected String getDeclaration() {		
		return 
			String.format(
				"public class %s implements NavBarElementStrategy {", 
				className);
	}

}
