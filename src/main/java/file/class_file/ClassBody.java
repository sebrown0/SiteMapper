/**
 * 
 */
package file.class_file;

import java.util.ArrayList;
import java.util.List;

import file.method.Method;
import file.variable.ClassVariable;
import file.variable.Variables;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for the class body of a 
 * site-mapped class file.
 * 
 */
public class ClassBody {
	private Variables vars;
//	private List<ClassVariable> variables = new ArrayList<>();
//	private List<Method> methods = new ArrayList<>();
	
//	public ClassBody addVariable(ClassVariable v) {
//		variables.add(v);
//		return this;
//	}
//	
//	public ClassBody addMethod(Method m) {
//		methods.add(m);
//		return this;
//	}

	@Override
	public String toString() {
		return null;
//		return insertVariables() + "\n" + insertMethods();
	}
	
//	private String insertVariables() {
//		String ret = "";
//		if(variables != null) {
//			for (ClassVariable v : variables) {
//				ret += String.format("%s\n", v.toString());
//			}
//		}
//		return ret;
//	}
//	
//	private String insertMethods() {
//		String ret = "";
//		if(methods != null) {
//			for (Method m : methods) {
//				ret += String.format("%s\n", m.toString());
//			}
//		}
//		return ret;
//	}
}
