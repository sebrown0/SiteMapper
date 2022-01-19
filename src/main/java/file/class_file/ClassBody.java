/**
 * 
 */
package file.class_file;

import java.util.Scanner;

import file.class_file.constructor.ClassConstructor;
import file.class_file.constructor.ExistingConstructorMapper;
import file.variable.ExistingVariableMapper;
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
	private ClassConstructor cnstr;
	
	private ClassBody(BodyBuilder b) {
		this.vars = b.vars;
		this.cnstr = b.cnstr;
	}
	
	public Variables getVars() {
		return vars;
	}
	public ClassConstructor getCnstr() {
		return cnstr;
	}
	
	@Override
	public String toString() {
		return null;
//		return insertVariables() + "\n" + insertMethods();
	}
	
	public abstract static class BodyBuilder {
		private Variables vars;
		private ClassConstructor cnstr;
		
		protected abstract ClassBody build();
		public abstract BodyBuilder setVars();
		public abstract BodyBuilder setConstructor();			
	}
	
	/** 
	 * @author SteveBrown
	 * @version 1.0
	 * 	Initial
	 * @since 1.0
	 * 
	 * Use the file scanner to get the existing
	 * lines from the file. Then map to POJOs.
	 */
	public static class ExistingClassBody extends BodyBuilder {
		private Scanner scanner;	
		
		public ExistingClassBody(Scanner scanner) {
			this.scanner = scanner;
		}
		
		public BodyBuilder setVars() {
			ExistingVariableMapper mapper = new ExistingVariableMapper(scanner);
			super.vars = mapper.mapVariables();			
			return this;
		}
		public BodyBuilder setConstructor() {
			ExistingConstructorMapper mapper = 
					new ExistingConstructorMapper(
							scanner, new ClassConstructor.ExistingConstructorBuilder(scanner));
			
			super.cnstr = mapper.mapConstructor();
			return this;
		}
		
		@Override
		public ClassBody build() {
			return new ClassBody(this);
		}
		
	}

}
//private String insertVariables() {
//String ret = "";
//if(variables != null) {
//for (ClassVariable v : variables) {
//	ret += String.format("%s\n", v.toString());
//}
//}
//return ret;
//}
//
//private String insertMethods() {
//String ret = "";
//if(methods != null) {
//for (Method m : methods) {
//	ret += String.format("%s\n", m.toString());
//}
//}
//return ret;
//}