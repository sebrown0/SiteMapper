/**
 * 
 */
package site_mapper.creators.navigation;

import java.util.regex.Pattern;



/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * 
 */
//public class NavBarElementCreator implements NavElementAdder {
public class NavBarElementCreator extends NavElementCreator {
	
//	private List<String> imports = new ArrayList<>();
//	private List<String> elements = new ArrayList<>();
//	
//	private String modName;
//	@SuppressWarnings("unused")
//	private String menuName;
//	private String pckage;
//	private String className;
//	private PackageHierarchy ph;
//	private String root;
//	private String parentPackage;// = "object_models.modules";
//	private SiteMapInfo info;
	
	private static final String NAV_PATH = "top_right_nav";
	
	public NavBarElementCreator() {
		super(NAV_PATH);
	}

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

//	@Override
//	public void addElement(String itemName) {
////		System.out.println(String.format("Add item [%s], in menu [%s], for module [%s]", itemName, menuName, modName)); // TODO - remove or log 	
//		addImport(itemName);
//		addElementToList(itemName);
//	}
//
//	private void addImport(String itemName) {
//		imports.add(String.format("\nimport " + StringUtils.replaceFwdSlashes(ph.getParentPackage(), ".") + ".common.nav.nav_bar_elements.NavBar%s;", itemName));
//	}
//	
//	private void addElementToList(String itemName) {
//		elements.add(String.format("{\n\t\t\t\"%s\", new NavBar%s(coreData)},", itemName, itemName));
//	}
	
//	@Override
//	public NavElementAdder setPackageHierarchy(PackageHierarchy ph) {
//		this.ph = ph;
//		this.root = ph.getRoot();
//		this.parentPackage = StringUtils.replaceFwdSlashes(ph.getParentPackage(), ".");
//		return this;
//	}	
//	@Override
//	public NavElementAdder setModuleName(String modName) {
//		this.modName = modName;
//		return this;
//	}
//	@Override
//	public NavElementAdder setMenuName(String menuName) {
//		this.menuName = menuName;
//		return this;
//	}
//	@Override
//	public NavElementAdder setSiteMapInfo(SiteMapInfo info) {
//		this.info = info;
//		return this;
//	}
	
	
//	private void setPackage() {
//		pckage = String.format("%s.%s.%s", parentPackage, modName, NAV_PATH);
//	}
//	
//	private String getPackage() {
//		return "package " + StringUtils.replaceFwdSlashes(ph.getParentPackage(), ".") + "." + modName + ".top_right_nav;";
//	}
	
	
//	private void setClassName() {
//		className = String.format("NavBar%sElements", StringUtils.asPascalCase(modName));
//	}
//	
//	private String getDeclaration() {		
//		return 
//			String.format(
//				"public class %s implements NavBarElementStrategy {", 
//				className);
//	}
//	
//	private String getImports() {
//		String res="";		
//		for(String s : imports) {
//			res+=s;
//		}
//		return res;
//	}
	
//	private Object getComment() {		
//		return new NewComment(null);
//	}
//	
//	private String getVars() {
//		return 
//			"\tprivate Map<String, NavBarElement> elements;\n" +
//			"\tprivate WebDriver driver;\n" +
//			"\tprivate CoreData coreData;";
//	}
	
//	private String getConstructor() {
//		return 
//			"\tpublic NavBarPayrollElements(CoreData coreData) {\n" +
//			"\t\tthis.coreData = coreData;\n" +
//			"\t\tthis.driver = coreData.getWebDriver();\n" +
//			"\t\tsetElements();\n" +
//			"\t}";
//	}
//	
//	private String getSetElements() {
//		String res =
//			"\tprivate void setElements(){\n" +
//			"\t\telements = Stream.of(new Object[][] {";
//		
//		for(String s : elements) {
//			res+=s;
//		}
//		//remove trailing comma
//		res = res.substring(0, res.length()-1);
//		
//		return res + "\n\t\t}).collect(Collectors.toMap(data -> (String) data[0], data -> (NavBarElement) data[1])); \n\t}";
//	}
	
//	private String getOverriddenFunctions() {
//		return 
//			"\n\t@Override\r\n" +
//			"\tpublic Map<String, NavBarElement> getElements() {\n" +
//			"\t\treturn elements;\n" +
//				"\t}\n\n" +
//
//			"\t@Override\n" +
//			"\tpublic QuickLinks getQuickLinks() {\n" +
//			"\t\treturn new QuickLinksPayroll(driver);\n" +
//			"\t}";
//	}

	/*
	 * MAY HAVE TO BE ABSTRACT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 */
//	@Override
//	public void writeNavClass() {
//		setPackage();
//		setClassName();
////		/object_models.modulespayrolltop_right_nav
//		
//		String filePath = root + "/" +  ph.getParentPackage() + "/" + modName + "/" + "top_right_nav";
//		FileWriter.writeFile(this, filePath, className + ".java");
////		object_models/modules/payroll/top_right_nav
////		object_models.modules.common.nav.nav_bar_elements.NavBarEmployeeCreation;
//		
////		object_models.modules.payroll.top_right_nav
//		System.out.println("\n\nwriteNavClass"); // TODO - remove or log 	
//		System.out.println("\n-------------"); // TODO - remove or log
//		System.out.println("\n\n" + this.toString()); // TODO - remove or log
//		System.out.println("\n\n Write to -> " + filePath + "/" + className + ".java"); // TODO - remove or log 	
//	}

}
