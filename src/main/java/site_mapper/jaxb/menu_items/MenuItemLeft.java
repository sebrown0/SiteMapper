/**
 * 
 */
package site_mapper.jaxb.menu_items;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.containers.Container;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

@XmlRootElement(name="MenuItem", namespace="LeftMenu")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItemLeft extends MenuItemDetails {
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="package")
	private String packageName;
	@XmlAttribute(name="class")
	private String className;	
	
	@XmlElement(name="Type", namespace="MenuItem")
	private MenuItemType menuItemType;	
	@XmlElement(name="Container", namespace="MenuItem")
	private List<Container> itemContainers;

	public MenuItemLeft() {}
	
	@Override 
	public String getName() {
		return name;
	}
	@Override 
	public String getClassName() {
		return className;
	}
	@Override 
	public MenuItemType getMenuItemType() {
		return menuItemType;
	}

	@Override
	public String getPackageName() {
		return packageName;
	}

	@Override
	public List<Container> getItemContainers() {
		return itemContainers;
	}
			
//		@Override
//		public String toString() {
//			return String.format(
//					"MenuItem [name=%s, packageName=%s, className=%s, menuItemType=%s, headerElements=%s, bodyElements=%s, footerElements=%s, menuPackageName=%s, moduleName=%s, siteMapInfo=%s]",
//					name, packageName, className, menuItemType, headerContainer, bodyContainer, footerContainer, menuPackageName,
//					moduleName, siteMapInfo);
//		}
						
}
	