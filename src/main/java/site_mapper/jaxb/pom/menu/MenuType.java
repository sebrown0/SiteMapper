/**
 * 
 */
package site_mapper.jaxb.pom.menu;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class MenuType {
//  private final String NAME;
//  private final String PACKAGE_NAME;
//    
//	public MenuType(String name, String packageName) {
//		NAME = name;
//		PACKAGE_NAME = packageName;
//	}
//	
	public abstract String getName();
	public abstract String getPackageName();
	
//	@Override
//	public String toString() {
//		return String.format("Menu [name=%s, packageName=%s, menuItems=[%s]]", NAME, PACKAGE_NAME, Formatter.getAsCommaSeparatedList(menuItems));
//	}
}
