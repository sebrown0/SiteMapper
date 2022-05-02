/**
 * 
 */
package site_mapper.creators.navigation;

import java.util.Arrays;
import java.util.List;

import file.imports.Import;
import file.imports.NewImport;
import site_mapper.creators.imports.FindImport;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.imports.UseImport;
import site_mapper.jaxb.menu_items.MenuItem;
import utils.FileWriter;
import utils.text_utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TopRightNavElementCreator extends NavElementCreator {
	private MenuItem item;
	private String itemName;
	private String objName;
	private boolean hasValidReturnObj;
	
	public TopRightNavElementCreator(
			ImportMatcher impMatcher, MenuItem item) {			
			super(impMatcher);
			
			this.item = item;
	
			setNames();
			setIsValidObject();
	}
				
	private void setNames() {
		itemName = "NavBar" + item.getClassName();
		objName = item.getClassName();
	}
	
	private void setIsValidObject() {
		hasValidReturnObj = 
				(objName != null && objName.length() > 0 
				&& !objName.equalsIgnoreCase("TODO"));
	}
	
	public void writeNavBarElement() {
		String filePath = 
			info.getRootDir() + "/" +
			info.getParentPackage() + "/" +
			"common/nav/nav_bar_elements/";
		
		FileWriter.writeFile(this, filePath, itemName + ".java");
	}
	
	private Object getImportForReturnObj() {
		if(hasValidReturnObj) {
			FindImport finder = new FindImport(objName, info);
			return finder.getPath();
		}
		return "";
	}

	protected String getPackageDeclaration() {
		return 
			"package " + 
			StringUtils.replaceFwdSlashes(ph.getParentPackage(), ".") + 
			".common.nav.nav_bar_elements;";
	}
			
	@Override //RequiredImports
	public void updateWithMatched(String imp) {
		imports.add(new NewImport(new UseImport(imp), impMatcher.getFoundImports()));
	}

	@Override //RequiredImports
	public List<String> getRequiredImports() {
		return
			Arrays.asList(
				"Closable", "NavBarElement", "CoreData");
	}

	@Override
	protected String getImports() {		
		if(requiredImportsAdded==false) {
			requiredImportsAdded=true;			
			imports.add(new NewImport(new UseImport("org.openqa.selenium.By"), foundImports));
			imports.add(new NewImport(new UseImport("org.openqa.selenium.WebElement"), foundImports));
			resolveImports();
		}
		
		String res = "";
		for (Import imp : imports) {
			res += imp.toString();
		}
		return res;
	}
	
	protected String getDeclaration() {		
		return 
			String.format(
				"public class %s extends NavBarElement {\n", 
				itemName);
	}
	
	protected String getVars() {
		return 
			String.format(
					"\tpublic static final String ORIGINAL_NAME = \"%s\";\n", 
					item.getName());			
	}
	
	protected String getConstructor() {		
		return 
			String.format(
				"\tpublic %s(CoreData coreData) {\n" +
				"\t\tsuper(coreData, ORIGINAL_NAME);\n" +
				"\t}", 
				itemName);
	}
	
	private String getCloseableMethod() {
		return 
			"\t@Override\n" +
			"\tpublic Closable clickElement() {\n" +
			"\t\tWebElement el = " + 
			"\n\t\t\tsuper.getNavBar().findElement(By.xpath(\".//li/a/i[contains(@class, '" + item.getFaFa() + "')]\"));\n" +
			"\t\tel.click();\r\n" +
			getReturnsObject() +
			"\t}";
	}
	
	private String getReturnsObject() {		
		if(hasValidReturnObj) {
			return "\t\treturn new " + objName + "(coreData);\n";
		}
		return "\t\treturn null; /**** TODO ****/\n";		
	}

	@Override
	protected String getOverriddenFunctions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setClassName() {
		className = String.format("NavBar%s", StringUtils.asPascalCase(modName));
	}
	
	@Override
	public String toString() {
		String res = 
			String.format(
				"%s\n\n%s\n%s\n\n%s%s%s\n%s\n\n%s\n\n}", 
				getPackageDeclaration(),
				getImports(),
				getImportForReturnObj(),				
				getComment(),
				getDeclaration(),
				getVars(),
				getConstructor(),
				getCloseableMethod());
			
		return res;
	}
	
}
