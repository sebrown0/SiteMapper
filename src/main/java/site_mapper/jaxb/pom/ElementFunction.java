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
@XmlRootElement(name="Function", namespace="Function")
@XmlAccessorType(XmlAccessType.FIELD)
public class ElementFunction {
//	The name is set from the element...
//	@XmlAttribute(name="name")
//	private String name;
	@XmlAttribute(name="isDefaultPass")
	private String defaultPass;
	@XmlAttribute(name="type")
	private String type;		
	@XmlAttribute(name="subtype")
	private String subtype;		
	
	private String prntName;
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
	public ElementFunction setSubtype(String subtype) {
		this.subtype = subtype;
		return this;
	}
	public ElementFunction setName(String name) {
		this.name = name;
		return this;
	}
	public ElementFunction setParentName(String name) {
		prntName = name;
		return this;
	}
	
	@Override
	public String toString() {
		String funcName = getFunctionName();
		String res = 
			getTestAnnotation() +
			getDeclaration(funcName) + 
			getDynamicTest(funcName) +
			"\t}";
		return res;
	}
	private String getFunctionName() {
		String res="";
		if(prntName != null) {
			res = 
				"Container" +
				Formatter.capitaliseFirstChar(prntName) + 
				Formatter.capitaliseFirstChar(name) + 
				"FunctionTest";
		}else {
			res = 
				Formatter.capitaliseFirstChar(subtype) + 
				Formatter.capitaliseFirstChar(name) + 
				"FunctionTest";
		}
		return res;
	}
	private String getTestAnnotation() {
		return String.format("\n\t@TestControl(type=\"%s\", subtype=\"%s\")\n", type, getSubtype());
	}
	private String getSubtype() {
		return (type.equalsIgnoreCase("container")) ? "none" : subtype;
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

	public String getName() {
		return name;
	}

	public String getDefaultPass() {
		return defaultPass;
	}

	public String getType() {
		return type;
	}
	
}
