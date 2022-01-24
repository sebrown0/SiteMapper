/**
 * 
 */
package helpers;

import static helpers.ExistingTestClassFileBuilder.ANNO_RESULT;
import static helpers.ExistingTestClassFileBuilder.ANNO_STR;

import file.annotation.ExistingAnnotation;
import file.class_file.body.ClassBody;
import file.class_file.body.ClassBody.BodyBuilder;
import file.class_file.constructor.ClassConstructor;
import file.class_file.constructor.ClassConstructor.ExistingConstructorBuilder;
import file.helpers.Lines;
import file.method.Method;
import file.method.Method.ExistingMethodBuilder;
import file.method.MethodList;
import file.variable.ClassVariable;
import file.variable.Variable;
import file.variable.Variables;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ExistingTestClassBodyBuilder extends BodyBuilder{
	public static final String VAR1_RESULT = 
			"\tpublic static final String PANEL_TITLE = \"Employee Details\";";
	public static final String VAR2_RESULT = 
			"\tpublic static final String MENU_TITLE = \"Employee Details\";";
	public static final String VAR3_RESULT = 
			"\tpublic static final String MENU_PARENT_NAME = \"Employees\";";
	public static final Variable VAR1 = new ClassVariable
			.ClassVarFromString(VAR1_RESULT)
			.withAnnotation(new ExistingAnnotation(ANNO_STR))
			.build(); 
	public static final Variable VAR2 = new ClassVariable
			.ClassVarFromString(VAR2_RESULT)
			.withAnnotation(new ExistingAnnotation(ANNO_STR))
			.build(); 
	public static final Variable VAR3 = new ClassVariable
			.ClassVarFromString(VAR3_RESULT)
			.withAnnotation(new ExistingAnnotation(ANNO_STR))
			.build(); 
	
	public static final String CONSTRUCTOR_DEC = 
			"public EmployeeDetails(CoreData coreData){";
	public static final Lines<Object> CNSTR_LINES = 
			new Lines<>()
				.addLine("super(coreData, PANEL_TITLE);")
				.addLine("buildMyControls();");
	
	public static final String BUILD_MY_CONTROLS_DEC = 
			"private void buildMyControls(){";
	public static final Lines<Object> CONTROLS_LINES = 
			new Lines<>()
				.addLine("List.of(")
				.addLine("\tnew ControlData(\"save\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='SAVE']\"))),")
				.addLine("\tnew ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF1']\"))),")
				.addLine("\tnew ControlData(\"code\", new ControlGetterTextOut(coreData, By.cssSelector(\"input[id='FORM_ID']\")))")
				.addLine(");")
				.addLine("super.buildPanelControls(myControls);");
	
	public static final String NOT_FROM_SITEMAPPER_DEC = 
			"private String aMethodNotFromSiteMapper(int idx){";
	public static final Lines<Object> NOT_FROM_SITEMAPPER_LINES = 
			new Lines<>()
				.addLine("String aStr = \"\";")
				.addLine("//do some stuff...")
				.addLine("")
				.addLine("return aStr;");
	
	public static final String BODY_RESULT =		
		ANNO_RESULT + "\n" +
		"\t" + VAR1_RESULT +
		"\n" +
		ANNO_RESULT + "\n" +
		"\t" + VAR2_RESULT +
		"\n" +
		ANNO_RESULT + "\n" +
		"\t" + VAR3_RESULT +
		"\n\n" +
		ANNO_RESULT + "\n" + 
		"\t" + CONSTRUCTOR_DEC +
		"\n" + CNSTR_LINES.withIndent("\t\t").toString() +				
		"\t}\n\n" +
		
		ANNO_RESULT + "\n" +
		"\t" + BUILD_MY_CONTROLS_DEC +
		"\n" + CONTROLS_LINES.withIndent("\t\t").toString() +
		"\t}\n" + 
		
		ANNO_RESULT + "\n" +
		"\t" + NOT_FROM_SITEMAPPER_DEC +
		"\n" + NOT_FROM_SITEMAPPER_LINES.withIndent("\t\t").toString() +				
		"\t}"; 
			
	@Override
	public BodyBuilder setVars() {
		Variables clazzVars = new Variables();
		clazzVars.addLine(VAR1);
		clazzVars.addLine(VAR2);
		clazzVars.addLine(VAR3);
		
		super.vars = clazzVars;			
		return this;
	}
	@Override
	public BodyBuilder setConstructor() {
		ExistingConstructorBuilder builder = 
				new ClassConstructor.ExistingConstructorBuilder();
		
		for (Object obj : CNSTR_LINES.getLines()) {
			builder.addLine(obj);
		}
		
		super.cnstr =
				builder
					.withAnnotation(ANNO_STR)
					.withConstructorDeclaration(CONSTRUCTOR_DEC)
					.build();
		
		return this;
	}

//	@Override
	public BodyBuilder setMethods() {		
		super.methods = 
			new MethodList()
				.addMethod(buildMethod(BUILD_MY_CONTROLS_DEC, CONTROLS_LINES))
				.addMethod(buildMethod(NOT_FROM_SITEMAPPER_DEC, NOT_FROM_SITEMAPPER_LINES));
		return this;
	}
	
	private Method buildMethod(String decStr, Lines<Object> methodLines) {
		ExistingMethodBuilder builder = new Method.ExistingMethodBuilder(1);
		
		for (Object obj : methodLines.getLines()) {
			builder.addLine(obj.toString());
		}
		
		return
			builder
				.withAnnotation(ANNO_STR)
				.withDeclarationStr(decStr)
				.build();		
	}

	@Override
	public ClassBody build() {
		((ExistingTestClassBodyBuilder) this.setVars().setConstructor()).setMethods();
		return new ClassBody(this);
	}
}
