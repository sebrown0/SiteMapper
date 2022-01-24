/**
 * 
 */
package file.class_file.constructor;

import org.apache.logging.log4j.LogManager;

import file.modifier.Modifier;
import file.variable.ArgumentList;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 */
public class ConstructorDeclarationMapper implements DeclarationMapper {
	private Modifier modifier;
	private String name;
	private ArgumentList cnstrArgs;
	
	protected int currentStart;
	protected int currentEnd;
	
	public static String mapSuperArgs(String superDec) {
		String res = "";
		if(superDec != null && superDec.contains("super(")) {
			res = 
				superDec
					.replace("\t", "")
					.replace("super(", "")
					.replace(");", "")
					.trim();			 	
		}
		return res;
	}
	
	@Override
	public void mapDeclaration(String cnstrStr) {
		if(cnstrStr != null) {
			setModifier(cnstrStr);
			setName(cnstrStr);
			setArgs(cnstrStr);			
		}		
	}
	
	protected void setModifier(String cnstrStr) {
		currentStart = cnstrStr.indexOf("p");
		currentEnd = cnstrStr.indexOf(" ");
		modifier = new Modifier(getSubStr(cnstrStr,currentStart, currentEnd));
	}
	protected void setName(String cnstrStr) {
		currentStart = currentEnd + 1;
		currentEnd = cnstrStr.indexOf("(", currentStart);
		name = getSubStr(cnstrStr,currentStart, currentEnd);
	}
	protected void setArgs(String cnstrStr) {
		currentStart = currentEnd + 1;
		currentEnd = cnstrStr.indexOf(")", currentStart);			
		
		if((currentEnd - currentStart) >= 3){
			String argStr = getSubStr(cnstrStr,currentStart, currentEnd);
			cnstrArgs = new ArgumentList();
			cnstrArgs.createArgList(argStr);
		}
	}

	protected String getSubStr(String str, int s, int e) {
		String res = null;
		try {
			res = str.substring(s, e);
		} catch (IndexOutOfBoundsException e1) {
			LogManager
				.getLogger(ConstructorDeclarationMapper.class)
				.error("Error getting sub string value");
		}
		return res;
	}
	
	public Modifier getModifier() {
		return modifier;
	}
	public String getName() {
		return name;
	}
	public ArgumentList getArgs() {
		return cnstrArgs;
	}
	
}
