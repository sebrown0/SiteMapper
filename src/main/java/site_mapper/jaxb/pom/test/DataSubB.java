package site_mapper.jaxb.pom.test;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name="2")
public class DataSubB extends Data {
	@XmlAttribute
	private String value;

	public DataSubB(){
		System.out.println("DataSubB"); // TODO - remove or log 	
	}
	@Override
	public String getValue() {
		return value;
	}

}
