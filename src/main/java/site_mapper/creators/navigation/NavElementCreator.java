/**
 * 
 */
package site_mapper.creators.navigation;

import java.util.ArrayList;
import java.util.List;

import file.comment.NewComment;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;
import utils.FileWriter;
import utils.StringUtils;



/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * 
 */
public abstract class NavElementCreator implements NavElementAdder {	
	private String root;	
	
	protected SiteMapInfo info;	
	protected PackageHierarchy ph;
	protected String modName;
	protected String className;
	protected String parentPackage;
	protected List<String> imports = new ArrayList<>();
	protected List<String> elements = new ArrayList<>();
	
	protected abstract String getCommonImports();
	protected abstract String getOverriddenFunctions();
	protected abstract String getDeclaration();
	protected abstract void setClassName();
//	protected abstract void addImport(MenuItem item);	
//	protected abstract void addElementToList(MenuItem item);
	
	
//	public void addElement(MenuItem item) {
////		System.out.println(String.format("Add item [%s], in menu [%s], for module [%s]", itemName, menuName, modName)); // TODO - remove or log 	
//		addImport(item);
//		addElementToList(item);
//	}
		
	@Override
	public NavElementAdder setPackageHierarchy(PackageHierarchy ph) {
		this.ph = ph;
		this.root = ph.getRoot();
		this.parentPackage = StringUtils.replaceFwdSlashes(ph.getParentPackage(), ".");
		return this;
	}	
	@Override
	public NavElementAdder setModuleName(String modName) {
		this.modName = modName;
		return this;
	}
//	@Override
//	public NavElementAdder setMenuName(String menuName) {
//		this.menuName = menuName;
//		return this;
//	}
	@Override
	public NavElementAdder setSiteMapInfo(SiteMapInfo info) {
		this.info = info;
		return this;
	}
		
//	private void setPackage() {
//		pckage = String.format("%s.%s.%s", parentPackage, modName, NAV_PATH);
//	}
	
	protected String getPackage() {
		return 
			"package " + 
			StringUtils.replaceFwdSlashes(ph.getParentPackage(), ".") + "." + 
			modName +  "." +
			ph.getPackageName() + ";";
//			".top_right_nav;";
	}
			

	
	protected String getImports() {
		String res="";		
		for(String s : imports) {
			res+=s;
		}
		return res;
	}
	
	protected Object getComment() {		
		return new NewComment(info);
	}
	
	protected String getVars() {
		return 
			"\tprivate Map<String, NavBarElement> elements;\n" +
			"\tprivate WebDriver driver;\n" +
			"\tprivate CoreData coreData;";
	}
	
	protected String getConstructor() {
		return 
			"\tpublic NavBarPayrollElements(CoreData coreData) {\n" +
			"\t\tthis.coreData = coreData;\n" +
			"\t\tthis.driver = coreData.getWebDriver();\n" +
			"\t\tsetElements();\n" +
			"\t}";
	}
	
	protected String getSetElements() {
		String res =
			"\tprivate void setElements(){\n" +
			"\t\telements = Stream.of(new Object[][] {";
		
		for(String s : elements) {
			res+=s;
		}
		//remove trailing comma
		res = res.substring(0, res.length()-1);
		
		return res + "\n\t\t}).collect(Collectors.toMap(data -> (String) data[0], data -> (NavBarElement) data[1])); \n\t}";
	}
	
	@Override
	public void writeNavClass() {
//		setPackage();
		setClassName();
//		/object_models.modulespayrolltop_right_nav
		
//		String filePath = root + "/" +  ph.getParentPackage() + "/" + modName + "/" + "top_right_nav";
		String filePath = root + "/" +  ph.getParentPackage() + "/" + modName + "/" + ph.getPackageName();
		FileWriter.writeFile(this, filePath, className + ".java");

		//	C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/java/object_models/modules/payroll/top_right_nav
		
//		object_models/modules/payroll/top_right_nav
//		object_models.modules.common.nav.nav_bar_elements.NavBarEmployeeCreation;
		
//		object_models.modules.payroll.top_right_nav
//		System.out.println("\n\nwriteNavClass"); // TODO - remove or log 	
//		System.out.println("\n-------------"); // TODO - remove or log
//		System.out.println("\n\n" + this.toString()); // TODO - remove or log
//		System.out.println("\n\n Write to -> " + filePath + "/" + className + ".java"); // TODO - remove or log 	
	}

}
