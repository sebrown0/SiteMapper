/**
 * 
 */
package site_mapper.jaxb.menu_items;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Type", namespace="MenuItemType")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MenuItemType {	
	private TypeAttributes attributes;
	private String type;
	
	@XmlElements({
		@XmlElement(name="JsPanelWithIFrame", type=JsPanelWithIFrame.class, namespace="MenuItemType")
	})
	public MenuItemType setAttributes(TypeAttributes js) {
		this.attributes = js;		
		return this;
	}
	
	@XmlAttribute(name="name")
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}	

	public TypeAttributes getAttributes() {
		return attributes;
	}
}
