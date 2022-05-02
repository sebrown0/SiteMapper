/**
 * 
 */
package file.class_package;

import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get a package name in the correct format. 
 * All lower case with words separated by an underscore.
 * 
 */
public class PackageNameResolver {
	private String pckge;
	
	public PackageNameResolver(String pckge) {
		this.pckge = pckge;
	}

	public String getPackageInCorrectFormat() {		
		if(potentialPackage() && withSpaces()) {			
			correctPackage();
		}		
		return pckge.toLowerCase();
	}

	private boolean potentialPackage() {
		return pckge != null && !pckge.equals("");
	}
	
	private boolean withSpaces() {
		return pckge.contains(" ");
	}
	
	private void correctPackage() {
		pckge = StringUtils.replaceSpacesWithUnderScore(pckge);
	}
}
