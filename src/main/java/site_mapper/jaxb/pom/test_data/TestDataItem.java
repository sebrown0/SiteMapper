/**
 * 
 */
package site_mapper.jaxb.pom.test_data;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="item", namespace="TestData")
public class TestDataItem {
	@XmlAttribute(name="id")
	private String id;
	@XmlAttribute(name="value")
	private String value;
	@XmlAttribute(name="insertWith")
	private String insertWith;
	
	public String getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	public String getInsertWith() {
		return insertWith;
	}
	public TestDataItem setId(String id) {
		this.id = id;
		return this;
	}
	public TestDataItem setValue(String value) {
		this.value = value;
		return this;
	}
	public TestDataItem setInsertWith(String insertWith) {
		this.insertWith = insertWith;
		return this;
	}
}
