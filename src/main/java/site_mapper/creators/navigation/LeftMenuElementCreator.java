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
	private MenuItem currentItem;
		
	public LeftMenuElementCreator(ImportMatcher impMatcher) {
		super(impMatcher);
		this.foundImports = impMatcher.getFoundImports();
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
		String res =				
				"\t\t@SuppressWarnings(\"unchecked\")\n" +
				"\t\tpublic Map<String, Optional<List<String>>> getAll(){\n" +
				"\t\t\treturn Stream.of(new Object[][] {";
		
		//Parents
		for (Map.Entry<String, List<String>> e : parents.entrySet()) {
			var namePascal = StringUtils.asPascalCase(e.getKey());
			var nameUpper = namePascal.toUpperCase();
			
			res += String.format("\n\t\t\t\t{\"%s\", Optional.of(%s)},", namePascal, nameUpper);
		}
				
		//Standalone
		for(String s : standAlone) {
			res += String.format("\n\t\t\t\t{%s.MENU_TITLE, Optional.empty()},", StringUtils.asPascalCase(s));
		}
		
		res = StringUtils.removeTrailingComma(res);
		res += "\n\t\t\t}).collect(Collectors.toMap(d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));		\r\n"
				+ "\t\t}";
		
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
			prntPackage = StringUtils.asCamelCase(itemsPackage);
			imp += prntPackage + ".";			
		}
		
		imp +=  currentItem.getClassName();//+ ";";		
		imports.add(new NewImport(new UseImport(imp), impMatcher.getFoundImports()));
			
		return prntPackage;
	}
	
	protected void addElementToList(String prntPackage) {
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

	private Object getParentDeclarations() {
		String res = "";
		for (Map.Entry<String, List<String>> e : parents.entrySet()) {
			var namePascal = StringUtils.asPascalCase(e.getKey());
			var nameUpper = namePascal.toUpperCase();
			
			res = 
				String.format(
						"\tprivate static final List<String> %s = Arrays.asList(", nameUpper);
			
			//Parents
			res += String.format("\n\t\t%s.MENU_TITLE,", StringUtils.asPascalCase(currentItem.getClassName()));
			res = StringUtils.removeTrailingComma(res);
			res += "\n\t);";
			
			res += String.format("\n\tpublic List<String> get%s() {\n\t\treturn %s;\n\t}", namePascal, nameUpper);
		}
		return res;
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
				getParentDeclarations(),
				getOverriddenFunctions());				
		 	
		return res;
	}

}
