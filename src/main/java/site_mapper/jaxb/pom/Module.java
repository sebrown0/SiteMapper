package site_mapper.jaxb.pom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.pom.menu.LeftMenu;
import site_mapper.jaxb.pom.menu.MenuType;
import site_mapper.jaxb.pom.menu.TopRightNav;

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
	 
  @XmlElement(name="LeftMenu", namespace="Module", type=LeftMenu.class)
	private MenuType left;
	
  @XmlElement(name="TopRightNav", namespace="Module", type=TopRightNav.class)
	private MenuType topRightNav;
	        
  public String getName() {
      return name;
  }
  
	public List<MenuType> getMenus() {
		List<MenuType> menus = new ArrayList<>(Arrays.asList(left, topRightNav));
		return menus;
	}
	  
}
