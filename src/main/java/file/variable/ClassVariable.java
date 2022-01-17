/**
 * 
 */
package file.variable;

import file.helpers.Formatter;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * POJO for class variable.
 * 
 */
public class ClassVariable extends Variable {
	private String modifier;
	private String value;
	private String staticVar;
	private String finalVar;
	private String[] leftSide;
	private String rightSide;
	
	public final int k = 3;
	
	public VariableSetter setValue(String value) {
		this.value = value;
		return this;
	}	

	@Override
	public String toString() {
		return String.format(
				"%s%s%s%s%s%s%s", 
				Formatter.getAnnotation(annotation), 
				Formatter.getValueOf(modifier),
				Formatter.getValueOf(" ", staticVar),
				Formatter.getValueOf(" ", finalVar),
				Formatter.getValueOf(" ", type), 
				Formatter.getValueOf(" ", name),
				Formatter.getVariableValue(value));
	}

	@Override
	public void setFromString(String varStr) {
		System.out.println(varStr); // TODO - remove or log 	
		
		if(varStr.contains("=")) {
			String[] rightAndLeft = varStr.split("=");
			leftSide = rightAndLeft[0].split(" ");
			rightSide =  rightAndLeft[1];
		}else {
			leftSide = varStr.split(" ");
		}
		
		if(leftSide != null) {
			if(mapModifier(leftSide[0])) {
				if(mapIsStatic(leftSide[1])) {
					mapIsFinal(leftSide[2]);
					mapType(leftSide[3]);
					mapName(leftSide[4]);
					if(hasValue()) {
						mapValue();
					}
				}else {
					if(mapIsFinal(leftSide[1])) {
						//move to type
						mapType(leftSide[2]);
						mapName(leftSide[3]);
						if(hasValue()) {
							mapValue();
						}
					}else {
						//move to type
						mapType(leftSide[1]);
						mapName(leftSide[2]);
						if(hasValue()) {
							mapValue();
						}						
					}
				}
			}else {
				//TODO error
			}
		}		
	}		
	
	private boolean mapModifier(String m) { 	
		if(Modifier.isValidModifier(m)) {
			modifier = m;
			return true;
		}		
		return false;
	}
	private boolean mapIsStatic(String s) {
		if(s.equals("static")) {
			staticVar = "static";
			return true;
		}else {
			return false;
		}
	}
	private boolean mapIsFinal(String s) {
//		System.out.println("->" + s + "<-"); // TODO - remove or log 	
		if(s.equals("final")) {
			finalVar = "final";
			return true;
		}else {
			return false;
		}
	}
	private boolean mapType(String t) {
		type = t;
		//TODO add checks for the type.
		return true;
	}
	private void mapName(String n) {
		name = n;
	}
	private boolean hasValue() {
		return (rightSide != null && rightSide.length() > 0) ? true : false;
	}
	private void mapValue() {
		value = rightSide;
	}
}
