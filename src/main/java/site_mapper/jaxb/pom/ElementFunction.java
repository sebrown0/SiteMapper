/**
 * 
 */
package site_mapper.jaxb.pom;

import file.helpers.Formatter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Function")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementFunction {
	@XmlAttribute(name="isDefaultPass")
	private String defaultPass;
	
	private String type;	
	private String name;
	
	public ElementFunction isDefaultPass(boolean defaultPass) {
		this.defaultPass = (defaultPass == true) ? "true" : "false";
		return this;
	}
	
	public boolean isDefaultPass() {
		return 
				(defaultPass != null && defaultPass.equalsIgnoreCase("true"))
				? true : false;
	}

	public ElementFunction setType(String type) {
		this.type = type;
		return this;
	}

	public ElementFunction setName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public String toString() {
		String funcName = type+Formatter.capitaliseFirstChar(name);
		String res = 
			getTestAnnotation() +
			getDeclaration(funcName) + 
			getDynamicTest(funcName) +
			"\t}";
		return res;
	}
	private String getTestAnnotation() {
		return "\n\t@TestControl(type=\"" + type + "\")\n";
	}
	private String getDeclaration(String funcName) {		
		return String.format("\tpublic DynamicTest %s () {\n", funcName);
	}
	private String getDynamicTest(String funcName) {
		String start = String.format("\t\treturn DynamicTest.dynamicTest(\"[%s]", funcName);
		if(isDefaultPass()) {
			return start + " *NOT IMPLEMENTED*\", () -> assertTrue(true));\n";
		}else {
			return start + "\", () -> fail(\"*NOT IMPLEMENTED*\"));\n";
		}
	}
	
}
