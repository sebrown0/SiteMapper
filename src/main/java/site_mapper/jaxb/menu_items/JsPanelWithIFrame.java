/**
 * 
 */
package site_mapper.jaxb.menu_items;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.ComponentWriter;
import site_mapper.creators.ComponentWriterJsPanelWithIFrame;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="JsPanelWithIFrame")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class JsPanelWithIFrame extends TypeAttributes {	
	private String panelTitle;	
	private String menuTitle;	
	private String menuParentName;
	
	@XmlElement(name="PanelTitle")
	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle; 	
	}
	@XmlElement(name="MenuTitle")
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	@XmlElement(name="MenuParent")
	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
	}
	
	public String getPanelTitle() {
		return panelTitle;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public String getMenuParentName() {
		return menuParentName;
	}
	
	@Override //TypeAttributes
	public String getExtends() {
		return "JsPanelWithIFrame";
	}
	@Override //TypeAttributes
	public ComponentWriter getComponentWriter() {
		return new ComponentWriterJsPanelWithIFrame();
	}

}
