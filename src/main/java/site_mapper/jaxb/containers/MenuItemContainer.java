/**
 * 
 */
package site_mapper.jaxb.containers;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Container", namespace="MenuItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class MenuItemContainer {
	@XmlElement(name="Container", namespace="MenuItem")
	private Container itemContainer;

	public Container getItemContainer() {
		return itemContainer;
	}

	public void setItemContainer(Container itemContainer) {
		this.itemContainer = itemContainer;
	}
		
}
