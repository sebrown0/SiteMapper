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
@XmlRootElement(name="Type")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MenuItemType {	
	private TypeAttributes js;
	private String type;

	public String getType() {
		return type;
	}	

	public TypeAttributes getJs() {
		return js;
	}
	
	@XmlElements({
		@XmlElement(name="JsPanelWithIFrame", type=JsPanelWithIFrame.class)
	})
	public void setJs(TypeAttributes js) {
		this.js = js;		
	}
	
	@XmlAttribute(name="name")
	public void setType(String type) {
		this.type = type;
	}
	
}
