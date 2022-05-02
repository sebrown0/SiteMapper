/**
 * 
 */
package site_mapper.creators.navigation.left_menu;

import static utils.text_utils.StringUtils.asPascalCase;
import static utils.text_utils.StringUtils.removeTrailingComma;
import static utils.text_utils.StringUtils.removeUnderScoresAndAsPascalCase;

import java.util.List;
import java.util.Map;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Take the parents, i.e. Employee and create function
 * to return all the items in the parent.
 * 
 * FOR EXAMPLE:
 * ------------
 * 	private static final List<String> EMPLOYEES = Arrays.asList(
 *		ContactNumbers.MENU_TITLE,
 *		SalaryDetails.MENU_TITLE
 *	);
 *	public List<String> getEmployees() {
 *		return EMPLOYEES;
 *	}
 * 
 * When all the parents and stand alone items have
 * been created, then create the getAll function.
 * FOR EXAMPLE:
 * ------------ 
 * public Map<String, Optional<List<String>>> getAll(){
 * 	return Stream.of(new Object[][] {
 * 		{"Employees", Optional.of(EMPLOYEES)},
 * 		{"Employee_others", Optional.of(EMPLOYEE_OTHERS)}
 *	}).collect(Collectors.toMap(d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));
 * }
 * 
 */
public class LeftMenuComposer {
	private Map<String, List<String>> parents;
	private List<String> standAlone;
	
	public LeftMenuComposer(Map<String, List<String>> parents, List<String> standAlone) {
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
				var itemName = e.getKey();
				var nameUpper = asPascalCase(itemName).toUpperCase();
				
				res += 
					String.format(
							"\n\tprivate static final List<String> %s = Arrays.asList(", nameUpper);
				
				for(String itm : e.getValue()) {
					res += 
						String.format(
							"\n\t\t%s.MENU_TITLE,", 
							asPascalCase(itm));	
				}
				res = getParentGetter(res, itemName, nameUpper);
			}	
		}		
		return res;
	}
	
	private String getParentGetter(String str, String itemName, String nameUpper) {
		String res = removeTrailingComma(str);
		res += "\n\t);";				
		res += 
			String.format(
				"\n\tpublic List<String> get%s() {\n\t\treturn %s;\n\t}\n", 
				removeUnderScoresAndAsPascalCase(itemName), nameUpper);
		
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
			var itemName = e.getKey();
			var nameUpper = asPascalCase(itemName).toUpperCase();			
			res += 
				String.format(
					"\n\t\t\t\t{\"%s\", Optional.of(%s)},", 
					removeUnderScoresAndAsPascalCase(itemName), nameUpper);
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
				+  "\t}";
		return res;
	}
}
