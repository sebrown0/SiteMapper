/**
 * 
 */
package file.imports;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ImportList {
	private List<Import> imports = new ArrayList<>();

	public ImportList() {}
	
	public ImportList(List<Import> imports) {
		this.imports = imports;
	}
	
	public void addImport(Import imp) {
		imports.add(imp);
	}
	
	public void addImports(List<Import> imps) {
		imports.addAll(imps);
	}
	
	public List<Import> getImports() {
		return imports;
	}
			
	@Override
	public String toString() {		
			String result = "";
			if(imports != null) {
				for (Import t : imports) { 	
					result += t.toString();
				}	
			}			
			return result;
		}	
}
