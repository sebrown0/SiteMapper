/**
 * 
 */
package site_mapper.creators.navigation;

import static utils.StringUtils.asPascalCase;
import static utils.StringUtils.removeTrailingComma;

import java.util.List;
import java.util.Map;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class LeftMenuEntry {
	private Map<String, List<String>> parents;
	private List<String> standAlone;
	
	public LeftMenuEntry(Map<String, List<String>> parents, List<String> standAlone) {
		this.parents = parents;
		this.standAlone = standAlone;
	}

	public String getMenuEntries() {
		String res = getFunctionDelaration();
		res = addParentItems(res);
		res = addStandaloneItems(res);
		res = closeFunction(res);
		
		return res;		
	}
	
	public Object getParentDeclarations() {
		String res = "";
		
		if(parents != null && parents.size() > 0) {
			for (Map.Entry<String, List<String>> e : parents.entrySet()) {
				var namePascal = asPascalCase(e.getKey());
				var nameUpper = namePascal.toUpperCase();
				
				res += 
					String.format(
							"\n\tprivate static final List<String> %s = Arrays.asList(", nameUpper);
				
				for(String itm : e.getValue()) {
					res += 
						String.format(
							"\n\t\t%s.MENU_TITLE,", 
							asPascalCase(itm));	
				}
				
				res = removeTrailingComma(res);
				res += "\n\t);";
				
				res += String.format("\n\tpublic List<String> get%s() {\n\t\treturn %s;\n\t}", namePascal, nameUpper);
			}	
		}		
		return res;
	}
	
	private String getFunctionDelaration() {
		return 
				"\t@SuppressWarnings(\"unchecked\")\n" +
				"\tpublic Map<String, Optional<List<String>>> getAll(){\n" +
				"\t\treturn Stream.of(new Object[][] {";
	}
	
	private String addParentItems(String res) {
		for (Map.Entry<String, List<String>> e : parents.entrySet()) {			
			var namePascal = asPascalCase(e.getKey());
			var nameUpper = namePascal.toUpperCase();			
			res += String.format("\n\t\t\t\t{\"%s\", Optional.of(%s)},", namePascal, nameUpper);
		}
		return res;
	}
	
	private String addStandaloneItems(String res) {
		for(String s : standAlone) {
			res += String.format("\n\t\t\t\t{%s.MENU_TITLE, Optional.empty()},", asPascalCase(s));
		}
		return res;
	}
	
	private String closeFunction(String res) {
		res = removeTrailingComma(res);
		res += "\n\t\t\t}).collect(Collectors.toMap("
				+  "d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));		\n"
				+  "\t\t}";
		return res;
	}
}
