package site_mapper.jaxb.pom;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.pom.menu.LeftMenu;
import site_mapper.jaxb.pom.menu.MenuType;
import site_mapper.jaxb.pom.menu.TopRightMenu;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Module found in the site_map.xml.
 */
@XmlRootElement(name="Module", namespace="Modules")
public class Module {	
	@XmlAttribute(name="name")
	private String name;
  
	@XmlElementWrapper(name="Menus", namespace="Module")
  @XmlElements(value = { 
      @XmlElement(name="Left", 
      						namespace="Menus",
                  type=LeftMenu.class),
      @XmlElement(name="TopRight",
      						namespace="Menus",
                  type=TopRightMenu.class)
    })
  private List<MenuType> menuss;
	        
  public String getName() {
      return name;
  }
	public List<MenuType> getMenus() {
		return menuss;
	}
	  
}
