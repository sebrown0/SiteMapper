/**
 * 
 */
package file.method;

import file.modifier.Modifier;
import file.variable.ArgumentList;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class MethodDeclarationMapper {
	private Modifier modifier;
	private String returnType;
	private String name;
	private ArgumentList args;
	private int currentStart;
	private int currentEnd;
	
	public void mapDeclaration(String cnstrStr) {
		if(cnstrStr != null) {
			setModifier(cnstrStr);
			setName(cnstrStr);
			setArgs(cnstrStr);			
		}
		
	}
	
	private void setModifier(String cnstrStr) {
		currentStart = cnstrStr.indexOf("p");
		currentEnd = cnstrStr.indexOf(" ");
		modifier = new Modifier(cnstrStr.substring(currentStart, currentEnd));
	}
	private void setName(String cnstrStr) {
		currentStart = currentEnd + 1;
		currentEnd = cnstrStr.indexOf("(", currentStart);
		name = cnstrStr.substring(currentStart, currentEnd);
	}
	private void setArgs(String cnstrStr) {
		currentStart = currentEnd + 1;
		currentEnd = cnstrStr.indexOf(")", currentStart);			
		
		String argStr = cnstrStr.substring(currentStart, currentEnd);
		args = new ArgumentList();
		args.createArgList(argStr);		
	}

	public Modifier getModifier() {
		return modifier;
	}

	public String getReturnType() {
		return returnType;
	}

	public String getName() {
		return name;
	}

	public ArgumentList getArgs() {
		return args;
	}
}
