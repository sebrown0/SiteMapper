package site_mapper.jaxb.pom;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

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
  @XmlElement(name="Menu", namespace="Menus")
  private List<Menu> menus;
        
  public String getName() {
      return name;
  }
	public List<Menu> getMenus() {
		return menus;
	}
	  
}
