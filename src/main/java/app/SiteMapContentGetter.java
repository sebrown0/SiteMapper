/**
 * 
 */
package app;

import java.util.Optional;

import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import app.xml_content.XmlContent;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;


/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class SiteMapContentGetter <T extends XmlContent> {	
	private Logger logger = LogManager.getLogger("site_mapper.app.log");
	private JAXBContext jc;
	private Unmarshaller unmarshaller;	
	private Optional<T> content = Optional.empty();	
	private final String XML_SOURCE;
	private final Class<T> clazz;
	
	public SiteMapContentGetter(final String XML_SOURCE, Class<T> clazz) {
		this.XML_SOURCE = XML_SOURCE;
		this.clazz = clazz;
	}
		
	public Optional<T> getContent() {
		try {
			setJaxContext(clazz);
			setUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		setContent(clazz);
		return content;
	}
	
	private void setJaxContext(Class<T> clazz) throws JAXBException {
		jc = JAXBContext.newInstance(clazz);
	}

	private void setUnmarshaller() throws JAXBException {
		unmarshaller = jc.createUnmarshaller();
    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);  
	}
	
	private Optional<StreamSource> getSource() {
		//TODO - Check source file.
		return Optional.ofNullable(new StreamSource(XML_SOURCE));
	}

	private Optional<T> getUnmarshalledContent(StreamSource s, Class<T> clazz){
		Optional<T> content = Optional.ofNullable(null);
		try {
			content = Optional.ofNullable(unmarshaller.unmarshal(s, clazz).getValue());			
		} catch (JAXBException e) {
			logger.error("Error unmarshalling source");
		}	 
		return content;
	}

	private void setContent(Class<T> clazz){
		getSource().ifPresentOrElse(
				src -> {
					content = getUnmarshalledContent(src, clazz);								
				}, 
				new Runnable() {					
					@Override
					public void run() {
						logger.error("Error getting the source [" + XML_SOURCE + "] for unmarshling");
					}
				});
	}

}
