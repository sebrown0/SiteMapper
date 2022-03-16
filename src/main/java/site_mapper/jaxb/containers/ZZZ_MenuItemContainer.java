/**
 * 
 */
package site_mapper.jaxb.containers;

import java.util.List;

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
public class ZZZ_MenuItemContainer {
	@XmlElement(name="Container", namespace="MenuItem")
	private List<Container> itemContainers;

	public List<Container> getItemContainer() {
		return itemContainers;
	}

	public void setItemContainer(List<Container> itemContainers) {
		this.itemContainers = itemContainers;
	}
		
}
