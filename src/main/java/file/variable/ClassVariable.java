/**
 * 
 */
package file.variable;

import file.annotation.SiteMapAnnotation;
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
	private final SiteMapAnnotation annotation;	
	private final String staticVar;
	private final String finalVar;
		
	public ClassVariable(Builder b) {		
		super.name = b.name;		
		super.modifier = b.modifier;
		super.type = b.type;
		super.value = b.value;
		this.annotation = b.annotation;
		this.staticVar = b.staticVar;
		this.finalVar = b.finalVar;
	}
	
	@Override
	public String toString() {		
		return String.format(
				"%s%s%s%s%s%s%s;", 
				Formatter.getAnnotation(annotation, "\t"), 
				Formatter.getValueOf("\t", modifier),
				Formatter.getValueOf(" ", staticVar),
				Formatter.getValueOf(" ", finalVar),
				Formatter.getValueOf(" ", type),				
				Formatter.getValueOf(" ", name),
				Formatter.getVariableValue(value.trim(), type));
	}

	public abstract static class Builder {
		protected SiteMapAnnotation annotation;
		protected String name;
		protected String modifier;
		protected String type;
		protected String value;
		protected String staticVar;
		protected String finalVar;
		
		public abstract ClassVariable build();
		public abstract FromString withAnnotation(SiteMapAnnotation a);
	}
	
	public static class FromString extends Builder {
		private String[] leftSide;
		private String rightSide;		
		private final String varStr;

		public FromString(String varStr) {
			this.varStr = varStr;
		}

		@Override
		public FromString withAnnotation(SiteMapAnnotation a) {
			this.annotation = a;
			return this;
		}
		
		@Override
		public ClassVariable build() {
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
			return new ClassVariable(this);		
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
			String res = rightSide.trim();
			if(res.endsWith(";")) {
				res = res.substring(0, res.length()-1);
			}
			value = res;
		}
	}
}
		

