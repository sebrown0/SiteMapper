package site_mapper.jaxb.pom.test;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({DataSubA.class, DataSubB.class})
public  class Data {
	
	public  String getValue() {
		return "yyyyy";
	}
}
