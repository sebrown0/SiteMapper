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
	
	private static final String NAV_PATH = "top_right_nav";
	
//	public NavBarElementCreator() {
//		super(NAV_PATH);
//	}

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
		
		System.out.println("NavBarElementCreator.toString() -> " + res); // TODO - remove or log 	
		return res;
	}
	
	@Override //NavElementAdder
	public void addElement(MenuItem item) { 	
		addImport(item);
		addElementToList(item);
	}
	
	protected void addImport(MenuItem item) {
		imports.add(
				String.format(
						"\nimport " + parentPackage + 
						".common.nav.nav_bar_elements.NavBar%s;", item.getName()));
	}
//	@Override
//	protected void addImport(String itemName) {
//		imports.add(
//				String.format(
//						"\nimport " + StringUtils.replaceFwdSlashes(ph.getParentPackage(), ".") + 
//						".common.nav.nav_bar_elements.NavBar%s;", itemName));
//	}

	
	protected void addElementToList(MenuItem item) {
		elements.add(
				String.format(
						"{\n\t\t\t\"%s\", new NavBar%s(coreData)},", 
						item.getName(), item.getName()));
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
			"\nimport object_models.top_right_nav_bar.common.NavBarElement;" + 
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