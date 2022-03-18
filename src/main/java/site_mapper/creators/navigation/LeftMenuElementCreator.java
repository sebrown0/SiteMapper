/**
 * 
 */
package site_mapper.creators.navigation;

import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class LeftMenuElementCreator implements NavElementAdder {
	private String modName;
	private String menuName;
	private PackageHierarchy ph;
	
	@Override
	public void addElement(String itemName) {
		System.out.println(String.format("Add item [%s], in menu [%s], for module [%s]", itemName, menuName, modName)); // TODO - remove or log
	}

	@Override
	public NavElementAdder setPackageHierarchy(PackageHierarchy ph) {
		this.ph = ph;
		return this;
	}	
	@Override
	public NavElementAdder setModuleName(String modName) {
		this.modName = modName;
		return this;
	}

	@Override
	public NavElementAdder setMenuName(String menuName) {
		this.menuName = menuName;
		return this;
	}

	@Override
	public void writeNavClass() {
		System.out.println("\n\n" + this.toString()); // TODO - remove or log
	}	
	
}
