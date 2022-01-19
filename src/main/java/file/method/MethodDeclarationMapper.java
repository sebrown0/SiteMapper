/**
 * 
 */
package file.method;

import file.class_file.constructor.ConstructorDeclarationMapper;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 */
public class MethodDeclarationMapper extends ConstructorDeclarationMapper {	
	private String returnType;
	
	@Override
	public void mapDeclaration(String cnstrStr) {
		if(cnstrStr != null) {
			setModifier(cnstrStr);
			setReturnType(cnstrStr);
			setName(cnstrStr);
			setArgs(cnstrStr);			
		}		
	}
	
	private void setReturnType(String cnstrStr) {
		currentStart = currentEnd + 1;
		currentEnd = cnstrStr.indexOf(" ", currentStart);
		returnType = cnstrStr.substring(currentStart, currentEnd);
	}
	
	public String getReturnType() {
		return returnType;
	}

}
