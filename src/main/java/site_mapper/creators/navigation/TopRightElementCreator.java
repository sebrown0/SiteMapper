/**
 * 
 */
package site_mapper.creators.navigation;

import file.comment.NewComment;
import site_mapper.creators.imports.FindImport;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.SiteMapInfo;
import utils.FileWriter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TopRightElementCreator {
	private MenuItem item;
	private SiteMapInfo info;
	private String itemName;
	private String objName;
	private boolean hasValidReturnObj;
	
	public TopRightElementCreator(MenuItem item, SiteMapInfo info) {
		this.item = item;
		this.info = info;
		
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
		String filePath = info.getRootDir() + "/object_models/modules/common/nav/nav_bar_elements/";
		FileWriter.writeFile(this, filePath, itemName + ".java");
	}
	
	@Override
	public String toString() {
		String res = 
			String.format(
				"%s\n%s\n%s\n\n%s%s%s\n%s\n\n%s\n\n}", 
				getPackage(),
				getCommonImports(),
				getImportForReturnObj(),				
				getComment(),
				getDeclaration(),
				getVars(),
				getConstructor(),
				getCloseableMethod());
			
		return res;
	}
	
	private Object getImportForReturnObj() {
		if(hasValidReturnObj) {
			FindImport finder = new FindImport(objName, info);
			return finder.getPath();
		}
		return "";
	}

	private String getPackage() {
		return "package object_models.modules.common.nav.nav_bar_elements;";
	}
	
	private String getCommonImports() {		
		return 
			"\nimport org.openqa.selenium.By;" + 
			"\nimport org.openqa.selenium.WebElement;" + 
			"\nimport object_models.helpers.Closable;" +
			"\nimport object_models.pages.homepage.CoreData;" +
			"\nimport object_models.modules.common.nav.NavBarElement;";
	}
	
	private Object getComment() {		
		return new NewComment(info);
	}
	
	private String getDeclaration() {		
		return 
			String.format(
				"public class %s extends NavBarElement {\n", 
				itemName);
	}
	
	private String getVars() {
		return 
			String.format(
					"\tpublic static final String ORIGINAL_NAME = \"%s\";\n", 
					item.getName());			
	}
	
	private String getConstructor() {		
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
	
}
