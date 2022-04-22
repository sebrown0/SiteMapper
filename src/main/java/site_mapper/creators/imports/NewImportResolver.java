/**
 * 
 */
package site_mapper.creators.imports;

import java.util.Map;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class NewImportResolver implements ImportResolver {
//	private FoundImports foundImports;
	private RequiredImports reqImports;
	
	public NewImportResolver(RequiredImports reqImports) {
//		this.foundImports = foundImports;
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
			//log
		}		
	}

}
