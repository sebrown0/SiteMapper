/**
 * 
 */
package site_mapper.creators;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class UseImport implements ImportType{
	private String importStr;
	
	public UseImport(String importStr) {
		this.importStr = importStr;
	}

	@Override
	public String getPath() {
		return "import " + importStr + ";";				
	}

}
