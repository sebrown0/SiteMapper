/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import file.ClassBody;
import file.SiteMapAnnotation;
import file.method.Method;
import file.method.MethodBody;
import file.variable.Argument;
import file.variable.ClassVariable;
import file.variable.MethodVariable;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
class VariableTests {
	private static final String ANNOTATION_RESULT = "@SiteMap(author=\"SB\", version=\"1.0.0\", date=\"01/01/2022\")";
	
	@Test
	void testAnnotation() {
		SiteMapAnnotation annotation = new SiteMapAnnotation("SB", "1.0.0", "01/01/2022");
		assertEquals(ANNOTATION_RESULT, annotation.toString());
	}
	@Test
	void testVariable_withString() {
		SiteMapAnnotation annotation = new SiteMapAnnotation("SB", "1.0.0", "01/01/2022");
		ClassVariable v = new ClassVariable();
		v
			.setAnnotation(annotation)
			.setModifier("public static final String")
			.setName("PANEL_TITLE")
			.setValue("Employee Details");
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tpublic static final String PANEL_TITLE = \"Employee Details\";", 
				v.toString());				 	
	}
	@Test
	void testVariable_withInt() {
		SiteMapAnnotation annotation = new SiteMapAnnotation("SB", "1.0.0", "01/01/2022");
		MethodVariable v = new MethodVariable();
		v
			.setAnnotation(annotation)
			.setModifier("public static final int")
			.setName("PANEL_TITLE")
			.setValue("1");
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tpublic static final int PANEL_TITLE = 1", 
				v.toString());				 	
	}	
	@Test
	void testArgument() {
		Argument v = new Argument("String", "str");		
		assertEquals("String str", v.toString());				 	
	}
	
	@Test
	void methodBody() {
		MethodBody body = new MethodBody();
		body.addLine("Line1").addLine("Line2");
		assertEquals("\t\tLine1\n\t\tLine2\n", body.toString());
	}
	
	@Test
	void testMethod() {
		MethodBody body = new MethodBody();
		body.addLine("Line1").addLine("Line2");
		
		Method m = new Method();
		m
			.setAnnotation(new SiteMapAnnotation("SB", "1.0.0", "01/01/2022"))
			.setModifier("private")
			.setReturnType("String")
			.setName("aMethod")
			.addVariables(new Argument("String", "str"))
			.addVariables(new Argument("Integer", "idx"))
			.setBody(body);
		
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tprivate String aMethod(String str, Integer idx){\n" +
				"\t\tLine1\n\t\tLine2" +
				"\n\t}", 
				m.toString());				 	
	}	
	
	@Test
	void testClassBody() {
		SiteMapAnnotation annotation = new SiteMapAnnotation("SB", "1.0.0", "01/01/2022");
		
		ClassVariable v1 = new ClassVariable();
		v1
			.setAnnotation(annotation)
			.setModifier("public static final String")
			.setName("PANEL_NAME")
			.setValue("Employee Details");
		
		ClassVariable v2 = new ClassVariable();
		v2		
			.setModifier("private int")
			.setName("idx");
		
		MethodBody methodBody = new MethodBody();
		methodBody.addLine("Line1").addLine("Line2");
		
		Method m = new Method();
		m
			.setAnnotation(annotation)
			.setModifier("private")
			.setReturnType("String")
			.setName("aMethod")
			.addVariables(new Argument("String", "str"))
			.addVariables(new Argument("Integer", "idx"))
			.setBody(methodBody);
						 	
		ClassBody classBody = new ClassBody();
		classBody
			.addVariable(v1)
			.addVariable(v2)
			.addMethod(m);
		System.out.println(classBody.toString()); // TODO - remove or log 	
		assertEquals(
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tpublic static final String PANEL_NAME = \"Employee Details\";\n" +
				"\tprivate int idx;\n\n" +
				"\t" + ANNOTATION_RESULT + "\n" +
				"\tprivate String aMethod(String str, Integer idx){\n" +
				"\t\tLine1\n\t\tLine2\n" +
				"\t}\n"
				, classBody.toString());
	}	
}
