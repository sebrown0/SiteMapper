/**
 * 
 */
package site_mapper.jaxb.pom;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * A record or hierarchy of all packages added to the start point (root).
 * The hierarchy can be retrieved in either:
 *  dot notation root.package1.package2, or
 *  fwd slash notation root/package1/package2
 */
public class PackageHierarchy {
	private final String ROOT;
	private final String PRNT_PACKAGE;
	private String current;
	private String packageName;	
	private Queue<String> packageNames = new LinkedList<String>();
	private String ret;
	
	public PackageHierarchy(SiteMapInfo info) {
		this.ROOT = info.getRootDir();
		this.PRNT_PACKAGE = info.getParentPackage();
		
		addCurrent(PRNT_PACKAGE);		
	}
		
//	public PackageHierarchy(String root, String current) {
//		this.root = root;
//		addCurrent(current);		
//	}

	public PackageHierarchy removeCurrent() {		
		packageNames.remove(current);		
		return this;
	}
	
	public PackageHierarchy reset(String moduleName) {
		current = "object_models/modules/" + moduleName;
		packageNames.clear();		
		packageNames.add(current);
		return this;
	}
	
	public PackageHierarchy addCurrent(String current) {
		this.current = current;
		packageNames.add(current);
		return this;
	}
	
	public String getCurrent() {
		return current;
	}
	
	public String getRoot() {
		return ROOT;
	}
	
	public String getParentPackage() {
		return PRNT_PACKAGE;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public Queue<String> getPackageNames() {
		return packageNames;
	}
	
	public String getHierarchyDotNotation() {
		return getHierarchy(".");
	}
	public String getHierarchyFwdSlashNotation() {
		return getHierarchy("/");
	}
	private String getHierarchy(String separator) {
		ret = "";
		packageNames.forEach(pn -> {			
				ret += pn + separator;				
		});
		return (ret.length()>0) ? ret.substring(0, ret.length()-1) : "";
	}

//	public PackageHierarchy setMenuPackageName(String packageName) {
//		this.packageName = packageName;
//		return this;
//	}
	
	@Override
	public String toString() {
		return "PackageHierarchy [root=" + ROOT + ", current=" + current + ", packageNames=" + packageNames + ", ret=" + ret
				+ "]";
	}
	
}
