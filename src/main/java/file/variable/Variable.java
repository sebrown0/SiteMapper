/**
 * 
 */
package file.variable;

import file.annotation.SiteMapAnnotation;
import file.modifier.Modifier;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Intial
 * @since 1.0
 * 
 * Abstract POJO for variables in SiteMap class file.
 */

public abstract class Variable {
	protected SiteMapAnnotation annotation;	
	protected String staticVar;
	protected String finalVar;
	
	protected String modifier;
	protected String type;
	protected String name;
	protected String value;
		
	public Variable(VariableBuilder b) {
		this.name = b.name;		
		this.modifier = b.modifier;
		this.type = b.type;
		this.value = b.value;
		this.annotation = b.annotation;
		this.staticVar = b.staticVar;
		this.finalVar = b.finalVar;
	}
	
	public abstract static class VariableBuilder {
		protected SiteMapAnnotation annotation;
		protected String name;
		protected String modifier;
		protected String type;
		protected String value;
		protected String staticVar;
		protected String finalVar;
		
		public abstract Variable build();
		public abstract VariableBuilder withAnnotation(SiteMapAnnotation a);
		public abstract VariableBuilder withVariable(String v);
	}
	
	public static class FromString extends VariableBuilder {
		private String[] leftSide;
		private String rightSide;		
		private String varStr;

		public FromString() {}
		public FromString(String varStr) {
			this.varStr = varStr;
		}

		@Override
		public VariableBuilder withAnnotation(SiteMapAnnotation a) {
			this.annotation = a;
			return this;
		}
		@Override
		public VariableBuilder withVariable(String v) {
			this.varStr = v;
			return this;
		}
		
		@Override
		public Variable build() {			
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
