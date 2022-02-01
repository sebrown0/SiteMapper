/**
 * 
 */
package file.class_file.body;

import java.util.List;
import java.util.Scanner;

import file.class_file.constructor.ClassConstructor;
import file.class_file.constructor.ExistingConstructorMapper;
import file.helpers.Formatter;
import file.helpers.Lines;
import file.method.ExistingMethodMapper;
import file.method.MethodList;
import file.variable.ExistingClassVariableMapper;
import file.variable.Variables;
import site_mapper.creators.ComponentWriter;
import site_mapper.creators.ControlDataFunction;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementConstructor;
import site_mapper.jaxb.pom.ElementFunction;
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
	private final ControlDataFunction dataFunc;
	private final Lines<String> dynamicTestMethods;
	
	public ClassBody(BodyBuilder b) {
		this.vars = b.vars;
		this.cnstr = b.cnstr;
		this.methods = b.methods;
		this.dataFunc= b.dataFunc;
		this.dynamicTestMethods = b.dynamicTestMethods;
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
		String res =
				String.format(
				"%s\n%s\n\n%s%s%s%s\n", 
				Formatter.getValueOf(vars),
				Formatter.getValueOf(cnstr),
				Formatter.getValueOf(dataFunc),
				Formatter.getValueOf(methods),
				Formatter.getNewLineIfValueExists(dynamicTestMethods),
				Formatter.getValueOfStripTrailing(dynamicTestMethods));
		return res;
	}
	
	public abstract static class BodyBuilder {
		protected Variables vars;
		protected ClassConstructor cnstr;		
		protected MethodList methods;
		protected Lines<String> dynamicTestMethods;
		
		private ControlDataFunction dataFunc;
		
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
		private ComponentWriter componentWriter;
		private SiteMapInfo info;
		private ElementClass clazz;
		
		public NewClassBody(ComponentWriter componentWriter, ElementClass clazz, SiteMapInfo info) {
			this.componentWriter = componentWriter;
			this.info = info;
			this.clazz = clazz;
		}

		@Override
		public BodyBuilder setVars() {			
			super.vars = 
				new Variables()
					.setLines(componentWriter.getClassVariables());
			return this;
		}

		@Override
		public BodyBuilder setConstructor() {
			super.cnstr = 
					new ClassConstructor.NewConstructorBuilder(info, (ElementConstructor) componentWriter)
						.withAnnotation()
						.withComponentInfo()
						.callsBuildMyControls(clazz.hasControlList())
						.build();
			
			return this;
		}

		public BodyBuilder setElements() {	
			super.dataFunc = new ControlBuilder((ElementClass) clazz).buildControlFunction();
			return this;
		}
		
		public BodyBuilder setDynamicTestMethods() {
			dynamicTestMethods = new Lines<>();
			List<ElementFunction> funcs = clazz.getElementFunctions();			
			funcs.forEach(f -> 
				dynamicTestMethods.addLine(
						new DynamicTestMethodBuilder(f, info).build()
				)
			);
			return this;
		}	
		
		@Override
		public ClassBody build() {
			setVars();
			setConstructor();
			setElements();
			setDynamicTestMethods();
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
			super.methods = mapper.mapMethods();
			return this;
		}
		

		public BodyBuilder setDynamicTestMethods() {
			// TODO Auto-generated method stub
			return null;
		}		
		
		@Override
		public ClassBody build() {
			return new ClassBody(this);
		}
		
	}

}