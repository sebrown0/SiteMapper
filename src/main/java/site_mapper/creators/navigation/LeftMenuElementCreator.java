/**
 * 
 */
package site_mapper.creators.navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import file.imports.Import;
import file.imports.NewImport;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.imports.UseImport;
import site_mapper.jaxb.menu_items.MenuItem;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class LeftMenuElementCreator extends NavElementCreator {
	private Map<String, List<String>> parents = new HashMap<>();
	private List<String> standAlone = new ArrayList<>();
	private LeftMenuEntry leftMenuEntry;
	private MenuItem currentItem;
		
	public LeftMenuElementCreator(ImportMatcher impMatcher) {
		super(impMatcher);
		
		this.foundImports = impMatcher.getFoundImports();
		this.leftMenuEntry = new LeftMenuEntry(parents, standAlone);
	}

	@Override //RequiredImports
	public List<String> getRequiredImports() {
		return Arrays.asList("LeftMenuElements");
	}	
		
	@Override //RequiredImports
	public void updateWithMatched(String imp) {
		imports.add(new NewImport(new UseImport(imp), impMatcher.getFoundImports()));
	}
		
	@Override
	protected String getImports() {		
		if(requiredImportsAdded==false) {
			requiredImportsAdded=true;
			imports.add(new NewImport(new UseImport("java.util.List"), foundImports));
			imports.add(new NewImport(new UseImport("java.util.Arrays"), foundImports));			
			imports.add(new NewImport(new UseImport("java.util.Map"), foundImports));			
			imports.add(new NewImport(new UseImport("java.util.Optional"), foundImports));
			imports.add(new NewImport(new UseImport("java.util.stream.Collectors"), foundImports));
			imports.add(new NewImport(new UseImport("java.util.stream.Stream"), foundImports));
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
		String res = leftMenuEntry.getMenuEntries();	
		return res;		
	}
	
	@Override //NavElementAdder
	public void addElement(MenuItem item) {		
		this.currentItem = item;
		String prntPackage = addImport();	//employeeOthers
		addElementToList(prntPackage);
	}
		
	private String addImport() {
		var itemsPackage = currentItem.getPackage();
		String prntPackage = null;
		String imp = ph.getHierarchyDotNotation() + ".";
				
		if(itemsPackage != null && itemsPackage.length() > 0) {
			prntPackage = StringUtils.asCamelCase(itemsPackage);
			imp += prntPackage + ".";			
		}
		
		imp +=  currentItem.getClassName();//+ ";";		
		imports.add(new NewImport(new UseImport(imp), impMatcher.getFoundImports()));
			
		return prntPackage;
	}
	
	private void addElementToList(String prntPackage) {
		if(prntPackage != null && prntPackage.length() >= 1) {
			if(parents.containsKey(prntPackage)) {
				var children = parents.get(prntPackage);
				children.add(currentItem.getClassName());
			}else {
				List<String> children = new ArrayList<>();
				children.add(currentItem.getClassName());
				parents.put(prntPackage, children);
			}
		}else {
			standAlone.add(currentItem.getClassName());
		}
	}	

	@Override
	protected void setClassName() {		
		className = String.format("LeftMenu%s", StringUtils.asPascalCase(modName));
	}

	@Override
	protected String getDeclaration() {		
		return "public class LeftMenuPayroll implements LeftMenuElements {";
	}
	
	@Override
	public String toString() {
		String res = 
			String.format(
				"%s\n\n%s\n%s\n%s\n%s\n%s\n}", 
				getPackageDeclaration(),
				getImports(),				
				getComment(),
				getDeclaration(),
				leftMenuEntry.getParentDeclarations(),
				getOverriddenFunctions());				
		 	
		return res;
	}

}
