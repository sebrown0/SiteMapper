package site_mapper.creators.navigation;

import java.util.regex.Pattern;

import site_mapper.jaxb.menu_items.MenuItem;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * 
 */
public class NavBarElementCreator extends NavElementCreator {

	@Override
	public String toString() {
		String res = 
			String.format(
				"%s\n%s\n%s\n%s\n%s\n%s\n\n%s\n\n%s\n%s\n}", 
				getPackage(),
				getCommonImports(),
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
	
	private void addImport(MenuItem item) {
		imports.add(
				String.format(
						"\nimport " + parentPackage + 
						".common.nav.nav_bar_elements.NavBar%s;", item.getClassName()));
	}
	
	private void addElementToList(MenuItem item) {		
		var itemNameAsNav = "NavBar" + item.getClassName(); 
		elements.add(
				String.format(
						"{\n\t\t\t%s.ORIGINAL_NAME, new %s(coreData)},", 
						itemNameAsNav, itemNameAsNav));
	}

	private void writeNavBarElement(MenuItem item) {
		new TopRightElementCreator(item, info).writeNavBarElement();
	}
	
	@Override
	protected void setClassName() {
		className = 
			String.format("NavBar%sElements", StringUtils.asPascalCase(modName));
	}
	
	@Override
	protected String getCommonImports() {
		String[] parts = parentPackage.split(Pattern.quote("."));
		String p = "ERROR";
		if(parts.length >= 0) {
			p=parts[0];
		}
		return 
			"\nimport java.util.Map;" + 
			"\nimport java.util.stream.Collectors;" + 
			"\nimport java.util.stream.Stream;" + 
			"\nimport org.openqa.selenium.WebDriver;" +
			"\nimport object_models.modules.common.nav.NavBarElement;" + 
			"\nimport " + p + ".pages.homepage.CoreData;" +
			"\nimport " + parentPackage + ".common.nav.nav_bar_elements.NavBarElementStrategy;" + 
			"\nimport " + parentPackage + ".common.nav.quick_links.QuickLinks;" +
			"\nimport " + parentPackage + ".common.nav.quick_links.QuickLinksPayroll;";
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
			"\t\treturn new QuickLinksPayroll(driver);\n" +
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
