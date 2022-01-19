/**
 * 
 */
package file.class_file;

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

	private ClassBody(BodyBuilder b) {
		this.vars = b.vars;
	}
	
	@Override
	public String toString() {
		return null;
//		return insertVariables() + "\n" + insertMethods();
	}
	
	public abstract static class BodyBuilder {
		private Variables vars;
		
		protected abstract ClassBody build();

		public void setVars(Variables vars) {
			this.vars = vars;
		}
	}
	
	public static class ExistingClassBody extends BodyBuilder {

		@Override
		public ClassBody build() {
			return new ClassBody(this);
		}
		
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

	public Variables getVars() {
		return vars;
	}
}
