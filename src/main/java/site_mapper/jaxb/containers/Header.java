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
public class Header {
	@XmlElement(name="Container", namespace="MenuItem")
	private Container headerContainer;

	public Container getHeaderContainer() {
		return headerContainer;
	}

	public void setHeaderContainer(Container headerContainer) {
		this.headerContainer = headerContainer;
	}
		
}
