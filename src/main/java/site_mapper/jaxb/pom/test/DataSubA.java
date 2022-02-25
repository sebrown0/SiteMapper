package site_mapper.jaxb.pom.test;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(name="1")
public class DataSubA extends Data {
    private String value;

		@Override
		public String getValue() {
			return value;
		}
}