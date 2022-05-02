/**
 * 
 */
package site_mapper.creators.navigation.left_menu;

import static utils.text_utils.StringUtils.asPascalCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import file.imports.Import;
import file.imports.NewImport;
import site_mapper.creators.imports.ImportMatcher;
import site_mapper.creators.imports.UseImport;
import site_mapper.creators.navigation.MenuElementFactoryUpdater;
import site_mapper.creators.navigation.NavElementCreator;
import site_mapper.jaxb.menu_items.MenuItem;
import utils.clazz.PackageNameResolver;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add elements to the left menu and create the 
 * LeftMenu[module] class from toString.
 * 
 */
public class LeftMenuElementCreator extends NavElementCreator implements MenuElementFactoryUpdater {
	private Map<String, List<String>> parents = new HashMap<>();
	private List<String> standAlone = new ArrayList<>();
	private LeftMenuComposer leftMenuComposer;
	private MenuItem currentItem;
		
	public LeftMenuElementCreator(ImportMatcher impMatcher) {
		super(impMatcher);

		if(impMatcher != null) {
			this.foundImports = impMatcher.getFoundImports();
		}
		this.leftMenuComposer = new LeftMenuComposer(parents, standAlone);
	}
	
	@Override
	public void updateMenuElementFactory() {
		LeftMenuElementFactoryUpdater updater = 
			new LeftMenuElementFactoryUpdater(this, parents, standAlone);
		
		updater.updateFactoryContents();
		updater.writeUpdatedContentToFile();
	}	

	@Override //RequiredImports
	public List<String> getRequiredImports() {
		return Arrays.asList("LeftMenuElements");
	}	
		
	@Override //RequiredImports
	public void updateWithMatched(String imp) {
		imports.add(new NewImport(new UseImport(imp), impMatcher.getFoundImports()));
	}
		
	@Override //NavElementCreator
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
		
	@Override //NavElementCreator
	protected String getOverriddenFunctions() {
		String res = leftMenuComposer.getMenuEntries();	
		return res;		
	}
	
	@Override //NavElementAdder
	public void addElement(MenuItem item) {		
		this.currentItem = item;
		String prntPackage = addImport();	
		addElementToList(prntPackage);
	}
		
	private String addImport() {
		var itemsPackage = currentItem.getPackage();
		String prntPackage = null;
		String imp = ph.getHierarchyDotNotation() + ".";
				
		if(itemsPackage != null && itemsPackage.length() > 0) {
			prntPackage = new PackageNameResolver(itemsPackage).getPackageInCorrectFormat();
			imp += prntPackage + ".";			
		}
		
		imp +=  currentItem.getClassName();
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

	@Override //NavElementCreator
	protected void setClassName() {		
		className = String.format("LeftMenu%s", asPascalCase(modName));
	}

	@Override //NavElementCreator
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
				leftMenuComposer.getParentDeclarations(),
				getOverriddenFunctions());				
		 	
		return res;
	}
	
}
