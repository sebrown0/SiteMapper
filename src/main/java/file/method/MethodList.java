/**
 * 
 */
package file.method;

import file.helpers.Lines;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Wrapper for a list of methods.
 */
public class MethodList {
	private Lines<Method> methods = new Lines<>();
	private String indent = "";
	
	public MethodList withIndent(String indent) {
		this.indent = indent;
		return this;
	}
	
	public MethodList addMethod(Method m) {
		methods.withIndent(indent).addLine(m);
		return this;
	}
	
	public Lines<Method> getMethods() {
		return methods;
	}

	public void setMethods(Lines<Method> methods) {
		this.methods = methods;
	}

	@Override
	public String toString() {
		return 
		  methods
				.toString()
				.stripTrailing();
	}	
	
}
