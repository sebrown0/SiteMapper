/**
 * 
 */
package helpers;

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
public class ExistingTestClassBodyBuilder extends BodyBuilder {
	private final ExistingTestClassFileBuilder classFileBuilder;
		
	public ExistingTestClassBodyBuilder(ExistingTestClassFileBuilder classFileBuilder) {
		this.classFileBuilder = classFileBuilder;
	}

	public static final String VAR1_RESULT = 
			"\tpublic static final String PANEL_TITLE = \"Employee Details\";";
	public static final String VAR2_RESULT = 
			"\tpublic static final String MENU_TITLE = \"Employee Details\";";
	public static final String VAR3_RESULT = 
			"\tpublic static final String MENU_PARENT_NAME = \"Employees\";";
	
	public Variable VAR1() {
		return
		new ClassVariable
				.ClassVarFromString("public static final String PANEL_TITLE = \"Employee Details\";")
				.withAnnotation(new ExistingAnnotation(classFileBuilder.ANNO_STR()))
				.build(); 
	}
	
	public Variable VAR2() {
		return 
			new ClassVariable
				.ClassVarFromString("public static final String MENU_TITLE = \"Employee Details\";")
				.withAnnotation(new ExistingAnnotation(classFileBuilder.ANNO_STR()))
				.build(); 
	}
	public Variable VAR3() {
		return
			new ClassVariable
				.ClassVarFromString("public static final String MENU_PARENT_NAME = \"Employees\";")
				.withAnnotation(new ExistingAnnotation(classFileBuilder.ANNO_STR()))
				.build(); 
	}
	public static final String CONSTRUCTOR_DEC = 
			"public EmployeeDetails(CoreData coreData){";
	public static final Lines<Object> CNSTR_LINES = 
			new Lines<>()
				.addLine("super(coreData, PANEL_TITLE);")
				.addLine("buildMyControls();");
	
	public static final String BUILD_MY_CONTROLS_DEC = 
			"private void buildMyControls() {";
	public static final Lines<Object> CONTROLS_LINES = 
			new Lines<>()
				.addLine("\tvar myControls =")
				.addLine("\t\tList.of(")
				.addLine("\t\t\tnew ControlData(\"save\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='SAVE']\"))),")
				.addLine("\t\t\tnew ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF1']\"))),")
				.addLine("\t\t\tnew ControlData(\"code\", new ControlGetterTextOut(coreData, By.cssSelector(\"input[id='FORM_ID']\")))")
				.addLine("\t\t);")	
				.addLine("\tsuper.buildPanelControls(myControls);");
	
	public static final String NOT_FROM_SITEMAPPER_DEC = 
			"private String aMethodNotFromSiteMapper(int idx) {";
	public static final Lines<Object> NOT_FROM_SITEMAPPER_LINES = 
			new Lines<>()
				.addLine("\tString aStr = \"\";")
				.addLine("\t//do some stuff...")
				.addLine("\t")
				.addLine("\treturn aStr;");

	public String TEST_METHOD_1() {
		return 
				classFileBuilder.ANNO_RESULT() + "\n" +
				"\t@TestControl(type=\"button\")\n" +
				"\tpublic DynamicTest buttonSave () {\n" +
				"\t\treturn DynamicTest.dynamicTest(\"[buttonSave] *NOT IMPLEMENTED*\", () -> assertTrue(true));\n" +
				"\t}"; 
	}
	
	public String TEST_METHOD_2() {
		return 
				classFileBuilder.ANNO_RESULT() + "\n" +
				"\t@TestControl(type=\"button\")\n" +
				"\tpublic DynamicTest buttonSearch () {\n" +
				"\t\treturn DynamicTest.dynamicTest(\"[buttonSearch]\", () -> fail(\"*NOT IMPLEMENTED*\"));\n" +
				"\t}\n";
	}
	
	public String BODY_RESULT() {
		return 
			classFileBuilder.ANNO_RESULT() + "\n" +
			VAR1_RESULT +
			"\n" +
			classFileBuilder.ANNO_RESULT() + "\n" +
			VAR2_RESULT +
			"\n" +
			classFileBuilder.ANNO_RESULT() + "\n" +
			VAR3_RESULT +
			"\n\n" +
			classFileBuilder.ANNO_RESULT() + "\n" + 
			"\t" + CONSTRUCTOR_DEC +
			"\n" + CNSTR_LINES.withIndent("\t\t").toString() +				
			"\t}\n\n" +
			
			classFileBuilder.ANNO_RESULT() + "\n" +
			"\t" + BUILD_MY_CONTROLS_DEC +
			"\n" + CONTROLS_LINES.withIndent("\t").toString() +
			"\t}";
			
	}

	public String BODY_RESULT_WITH_TEST_METHODS() {		
		return
			BODY_RESULT() +	"\n" +		
			TEST_METHOD_1() + "\n" +
			TEST_METHOD_2();// + "\n" ; 
	}		
	
	public String BODY_RESULT_WITH_TEST_METHODS_AND_EXTRA_METHOD() {		
		return
			BODY_RESULT() +		
			
			"\n\t" + NOT_FROM_SITEMAPPER_DEC +
			"\n" + NOT_FROM_SITEMAPPER_LINES.withIndent("\t").toString() +				
			"\t}\n" +
			
			TEST_METHOD_1() + "\n" +
			TEST_METHOD_2();// + "\n" ; 
	}		
	
	public String BODY_RESULT_WITH_EXTRA_METHOD() {		
		return
			BODY_RESULT() +		
			"\n\t" + NOT_FROM_SITEMAPPER_DEC +
			"\n" + NOT_FROM_SITEMAPPER_LINES.withIndent("\t").toString() +				
			"\t}"; 
	}		
		
	@Override
	public BodyBuilder setVars() {
		Variables clazzVars = new Variables();
		clazzVars.addLine(VAR1());
		clazzVars.addLine(VAR2());
		clazzVars.addLine(VAR3());
		
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
					.withAnnotation(classFileBuilder.ANNO_STR())
					.withConstructorDeclaration(CONSTRUCTOR_DEC)
					
					.build();
		
		return this;
	}
	
	public BodyBuilder setMethods() {		
		super.methods = 
			new MethodList()
				.addMethod(buildMethod(BUILD_MY_CONTROLS_DEC, CONTROLS_LINES, classFileBuilder.ANNO_STR()))
				.addMethod(buildMethod(NOT_FROM_SITEMAPPER_DEC, NOT_FROM_SITEMAPPER_LINES));
		return this;
	}
	
	public BodyBuilder setDynamicTestMethods() {
		Lines<String> testMethods = new Lines<>();
		testMethods.addLine(TEST_METHOD_1());
		testMethods.addLine(TEST_METHOD_2());
		super.dynamicTestMethods = testMethods; 	
		return this;
	}
	
	private Method buildMethod(String decStr, Lines<Object> methodLines, String anno) {
		ExistingMethodBuilder builder = new Method.ExistingMethodBuilder(1);
		
		for (Object obj : methodLines.getLines()) {
			builder.addLine(obj.toString());
		}
		
		return
			builder
				.withSiteMapAnnotation(anno)
				.withDeclarationStr(decStr)
				.build();		
	}
	private Method buildMethod(String decStr, Lines<Object> methodLines) {
		ExistingMethodBuilder builder = new Method.ExistingMethodBuilder(1);
		
		for (Object obj : methodLines.getLines()) {
			builder.addLine(obj.toString());
		}
		
		return
			builder
				.withDeclarationStr(decStr)
				.build();		
	}

	@Override
	public ClassBody build() {
		((ExistingTestClassBodyBuilder) ((ExistingTestClassBodyBuilder) this.setVars().setConstructor()).setMethods()).setDynamicTestMethods();
		return new ClassBody(this);
	}

}
