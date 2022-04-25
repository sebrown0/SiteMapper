/**
 * 
 */
package site_mapper.creators.imports;

import java.util.Map;

import org.apache.logging.log4j.LogManager;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Try and match the known/resolved imports
 * with those required for the item.
 */
public class NewImportResolver implements ImportResolver {
	private RequiredImports reqImports;
	
	public NewImportResolver(RequiredImports reqImports) {
		this.reqImports = reqImports;
	}

	@Override
	public void resolveImports(FoundImports foundImports) {
		Map<String,String> imps = foundImports.getResolvedImports();
		if(imps != null) {
			reqImports.getRequiredImports().forEach(imp ->{
				var foundImp = imps.get(imp);
				if(foundImp != null) {					
					reqImports.updateWithMatched(foundImp);
				}
			});	
		}else {
			LogManager
				.getLogger()
				.info("Resolved imports is empty so cannot match required with resolved");
		}		
	}

}
