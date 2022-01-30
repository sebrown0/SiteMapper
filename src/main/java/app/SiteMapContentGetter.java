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
import site_mapper.jaxb.pom.PomMapperApp;


/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class SiteMapContentGetter <T extends XmlContent> {
	private Logger logger = LogManager.getLogger(this.getClass());
	private JAXBContext jc;
	private Unmarshaller unmarshaller;
	
	private Optional<T> content = Optional.empty();
	
	private final String XML_SOURCE;
	
	public SiteMapContentGetter(final String XML_SOURCE) {
		this.XML_SOURCE = XML_SOURCE;	
	}
	
	public Optional<T> getContent(Class<T> clazz) {
		try {
			setJaxContext(clazz);
			unmarshallSource();
			getSource().ifPresentOrElse(
					src -> {
						content = getMapperNew(src, clazz);								
					}, 
					new Runnable() {					
						@Override
						public void run() {
							logger.error("Error getting the source [" + XML_SOURCE + "] for unmarshling");
						}
					});
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	private Optional<StreamSource> getSource() {
		//TODO - Check source file.
		return Optional.ofNullable(new StreamSource(XML_SOURCE));
	}

	private Optional<T> getMapperNew(StreamSource s, Class<T> clazz){
		Optional<T> app = Optional.ofNullable(null);
		try {
			app = Optional.ofNullable(unmarshaller.unmarshal(s, clazz).getValue());			
		} catch (JAXBException e) {
			System.out.println(e);
			logger.error("Error unmarshalling source");
		}	 
		return app;
	}

	private void writeLogHeader() {
		logger.info("Creating POMs");
	}
	
	private void setJaxContext(Class<T> clazz) throws JAXBException {
		jc = JAXBContext.newInstance(clazz);
	}
	private void unmarshallSource() throws JAXBException {
		unmarshaller = jc.createUnmarshaller();
    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);  
	}
	

}
