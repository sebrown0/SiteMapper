/**
 * 
 */
package site_mapper.creators.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import site_mapper.jaxb.menu_items.MenuItem;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class LeftMenuElementCreator  extends NavElementCreator {
	private Map<String, List<String>> parents = new HashMap<>();
	private MenuItem currentItem;
	
	@Override
	protected String getCommonImports() {
		return 
			"\nimport java.util.Arrays;" + 
			"\nimport java.util.List;" + 
			"\nimport java.util.Map;" + 
			"\nimport java.util.Optional;" +
			"\nimport java.util.stream.Collectors;" + 
			"\nimport java.util.stream.Stream;" +
			"\nimport object_models.common.nav.LeftMenuElements;";
	}
	
	@Override
	protected String getOverriddenFunctions() {				
		String res =				
				"\t\t@SuppressWarnings(\"unchecked\")\n" +
				"\t\tpublic Map<String, Optional<List<String>>> getAll(){\n" +
				"\t\t\treturn Stream.of(new Object[][] {";
		
		for (Map.Entry<String, List<String>> e : parents.entrySet()) {
			var namePascal = StringUtils.asPascalCase(e.getKey());
			var nameUpper = namePascal.toUpperCase();
			
//			res += String.format("\n\t\t\t{\"%s\",  Optional.of(%s)};", namePascal, nameUpper);
			res += String.format("\n\t\t\t\t{\"%s\", Optional.of(%s)},", namePascal, nameUpper);
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
	protected String addImport() {
		String prntPackage = StringUtils.asCamelCase(currentItem.getPackage());
		String imp="\nimport " + ph.getHierarchyDotNotation() + "." + prntPackage  + "." + currentItem.getClassName()+ ";";
		imports.add(imp);
		
		return prntPackage;
	}
	
	protected void addElementToList(String prntPackage) {
		if(prntPackage != null) {
			if(parents.containsKey(prntPackage)) {
				var children = parents.get(prntPackage);
				children.add(currentItem.getClassName());
			}else {
				List<String> children = new ArrayList<>();
				children.add(currentItem.getClassName());
				parents.put(prntPackage, children);
			}
		}else {
//			add to children
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
		String res = null;
		for (Map.Entry<String, List<String>> e : parents.entrySet()) {
			var namePascal = StringUtils.asPascalCase(e.getKey());
			var nameUpper = namePascal.toUpperCase();
			
			res = 
				String.format(
						"\tprivate static final List<String> %s = Arrays.asList(", nameUpper);
			for(String c : e.getValue()) {
				res += String.format("\n\t\t%s.MENU_TITLE,", StringUtils.asPascalCase(currentItem.getClassName()));
				res = StringUtils.removeTrailingComma(res);
			}
			res += "\n\t);";
			
			res += String.format("\n\tpublic List<String> get%s() {\n\t\treturn %s;\n\t}", namePascal, nameUpper);
		}
		return res;
	}
	
	@Override
	public String toString() {
		String res = 
			String.format(
				"%s\n%s\n%s\n%s\n%s\n%s\n%s\n}", 
				getPackage(),
				getCommonImports(),
				getImports(),
				getComment(),
				getDeclaration(),
				getParentDeclarations(),
				getOverriddenFunctions());				
		 	
		return res;
	}

}