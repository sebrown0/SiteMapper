/**
 * 
 */
package file.helpers;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ExistingFileDetails {
	private SiteMapInfo info;
	private ElementClass clazz;
	private PackageHierarchy ph;
	
	public ExistingFileDetails(ElementClass clazz, PackageHierarchy ph) {
		this.info = clazz.getSiteMapInfo();
		this.clazz = clazz;
		this.ph = ph;
	}
	
	public String getPath() {
		return 	
				info.getRootDir() + "/" + 
				info.getParentPackage() + "/" +
				clazz.getModuleName() + "/" +
				ph.getPackageName() + "/" + 
				ph.getCurrent();
	}
	
	public String getFileWithClassFileExtension() {
		return clazz.getClassName() + ".java";
	}
	
	@Override 
	public String toString() {
		if(info != null && clazz != null && ph != null) {
			return getPath() + "/" + getFileWithClassFileExtension();
		}else {
			return "";
		}
	}
}
