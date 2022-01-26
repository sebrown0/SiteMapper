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
	
	public List<Import> getImports() {
		return imports;
	}
	
//	public void setImports(List<Import> imports) {
//		this.imports = imports;
//	}
		
	@Override
	public String toString() {		
			String result = "";
			if(imports != null) {
				for (Import t : imports) {
//				System.out.println("->" + t.toString()); // TODO - remove or log 	
				result += t.toString();
				}	
			}			
			return result;
		}	
}

//public class ImportList {
////	private List<Import> imports = new ArrayList<>();
//	private Lines<Import> imports = new Lines<>();
//	public ImportList() {}
//	
//	public ImportList(List<Import> imports) {
//		this.imports.setLines(imports);
//	}
//
//	public List<Import> getImports() {
//		return imports.getLines();
//	}
//
//	public void setImports(List<Import> imports) {
//		this.imports.setLines(imports);
//	}
//		
//	@Override
//	public String toString() {
//		return imports.toString();	
//	}
//}