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
	
	public MethodList addMethod(Method m) {
		methods.addLine(m);
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
		return methods.toString();
	}	
	
}
