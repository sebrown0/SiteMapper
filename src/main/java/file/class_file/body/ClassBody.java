/**
 * 
 */
package file.class_file.body;

import java.util.Scanner;

import file.class_file.constructor.ClassConstructor;
import file.class_file.constructor.ExistingConstructorMapper;
import file.helpers.Formatter;
import file.method.ExistingMethodMapper;
import file.method.MethodList;
import file.variable.ExistingClassVariableMapper;
import file.variable.Variables;
import site_mapper.creators.ComponentWriter;
import site_mapper.elements.ElementConstructor;
import site_mapper.jaxb.pom.SiteMapInfo;

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
	private final Variables vars;
	private final ClassConstructor cnstr;
	private final MethodList methods;
	
	public ClassBody(BodyBuilder b) {
		this.vars = b.vars;
		this.cnstr = b.cnstr;
		this.methods = b.methods;
	}
	
	public Variables getVars() {
		return vars;
	}
	public ClassConstructor getCnstr() {
		return cnstr;
	}
	public MethodList getMethods() {
		return methods;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s\n%s\n\n%s", 
				Formatter.getValueOf(vars),
				Formatter.getValueOf(cnstr),
				Formatter.getValueOf(methods));
	}
	
	public abstract static class BodyBuilder {
		protected Variables vars;
		protected ClassConstructor cnstr;
		protected MethodList methods;
		
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
	 * Create a new ClassBody from ComponentWriter.
	 */
	public static class NewClassBody extends BodyBuilder {
		private ComponentWriter componentInfo;
		private SiteMapInfo info;
		
		public NewClassBody(ComponentWriter componentWriter, SiteMapInfo info) {
			this.componentInfo = componentWriter;
			this.info = info;
		}

		@Override
		public BodyBuilder setVars() {			
			super.vars = 
				new Variables()
					.setLines(componentInfo.getClassVariables());
			return this;
		}

		@Override
		public BodyBuilder setConstructor() {
			super.cnstr = 
					new ClassConstructor.NewConstructorBuilder(info, (ElementConstructor) componentInfo)
						.withAnnotation()
						.withComponentInfo()
						.build();
			
			return this;
		}

		/* 
		 * Take the list of elements from ElementClass
		 * and add them to the buildMyControls function.
		 */
		public BodyBuilder setElements() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public ClassBody build() {
			setVars();
			setConstructor();
			setElements();
			return new ClassBody(this);
		}	
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
		@Override
		public BodyBuilder setVars() {
			ExistingClassVariableMapper mapper = new ExistingClassVariableMapper(scanner);
			super.vars = mapper.mapVariables();			
			return this;
		}
		@Override
		public BodyBuilder setConstructor() {
			ExistingConstructorMapper mapper = 
					new ExistingConstructorMapper(
							scanner, new ClassConstructor.ExistingConstructorBuilder());
			
			super.cnstr = mapper.mapConstructor();
			return this;
		}

		public ExistingClassBody setMethods() {
			ExistingMethodMapper mapper = 
					new ExistingMethodMapper(scanner, 1);
			super.methods = mapper.mapMethods().withIndent("\t");
			return this;
		}
		
		@Override
		public ClassBody build() {
			return new ClassBody(this);
		}		
	}

}