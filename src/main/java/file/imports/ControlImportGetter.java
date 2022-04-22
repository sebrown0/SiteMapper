/**
 * 
 */
package file.imports;

import site_mapper.creators.imports.FindImport;
import site_mapper.creators.imports.FoundImports;
import site_mapper.jaxb.pom.SiteMapInfo;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Find the import from resolved imports.
 */
public class ControlImportGetter {

	public static Import getImportForContolGetter(String type, SiteMapInfo info, FoundImports resolvedImports) {
		final String impStr = "ControlGetter" + StringUtils.asPascalCase(type);
		return new NewImport(new FindImport(impStr, info), resolvedImports);
	}
	
}
