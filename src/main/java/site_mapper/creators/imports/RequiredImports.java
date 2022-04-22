/**
 * 
 */
package site_mapper.creators.imports;

import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface RequiredImports {
	List<String> getRequiredImports();
	void updateWithMatched(String imp);
}
