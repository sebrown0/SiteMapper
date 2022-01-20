/**
 * 
 */
package helpers;

import file.annotation.ExistingAnnotation;
import file.class_file.ClassBody;
import file.class_file.ClassBody.BodyBuilder;
import file.class_file.constructor.ClassConstructor;
import file.method.Method;
import file.method.MethodList;
import file.variable.ClassVariable;
import file.variable.Variables;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestClassBodyBuilder extends BodyBuilder{
	private final String ANNO = "author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\"";
	
	@Override
	public BodyBuilder setVars() {
		Variables clazzVars = new Variables();
		
		clazzVars.addLine( 
				new ClassVariable
					.ClassVarFromString("public static final int PANEL_TITLE = 1")
					.withAnnotation(new ExistingAnnotation(ANNO))
					.build());
		
		clazzVars.addLine( 
				new ClassVariable
					.ClassVarFromString("public static final String MENU_TITLE = \"Employee Details\";")
					.build());
		
		
		
		super.vars = clazzVars;			
		return this;
	}

	@Override
	public BodyBuilder setConstructor() {		
		final String DEC = "public EmployeeDetails(CoreData coreData) {";
		super
			.cnstr =			
				new ClassConstructor
					.ExistingConstructorBuilder()
					.withAnnotation(ANNO)
					.withConstructorDeclaration(DEC)
					.build();
		
		return this;
	}

	@Override
	public BodyBuilder setMethods() {
		Method m = 
			new Method.ExistingMethodBuilder(1)
				.withAnnotation(ANNO)
				.withDeclarationStr("private String aMethod(String str, Integer idx)")
				.addLine("Line1")
				.addLine("Line2")
				.build();		
		
		super.methods = new MethodList().addMethod(m);
		
		return this;
	}

	@Override
	public ClassBody build() {
		this.setVars().setConstructor().setMethods();
		return new ClassBody(this);
	}
}
