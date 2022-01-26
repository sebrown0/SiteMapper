/**
 * 
 */
package app;

import java.util.Optional;

import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

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
public class PomMapper {
	private Logger logger = LogManager.getLogger(this.getClass());
	private JAXBContext jc;
	private Unmarshaller unmarshaller;
	
	private final String XML_SOURCE;
	
	public PomMapper(final String XML_SOURCE) {
		this.XML_SOURCE = XML_SOURCE;	
	}
	
//	public Optional<Object> getUnmarshalledSource() {
//		Optional<PomMapperApp> pomMapperApp = Optional.ofNullable(null);
//		try {
//			setJaxContext();
//			unmarshallSource();
//			getSource().ifPresent(src -> getMapper(src).ifPresent(m -> pomMapperApp = Optional.ofNullable(m)));
////			pomMapperApp = getMapper(null)
//		} catch (JAXBException e) {
//			logger.error("Could not unmarshall XML");
//		}
//		return Optional.ofNullable(pomMapperApp);
//	}
	
	public void createPoms() {
		writeLogHeader();
		try {
			setJaxContext();
			unmarshallSource();
			mapPoms();
		} catch (JAXBException e) {
			logger.error("Could not create JAXB context. Quitting");
		}
	}
	
	private void writeLogHeader() {
		logger.info("Creating POMs");
	}
	
	private void setJaxContext() throws JAXBException {
		jc = JAXBContext.newInstance(PomMapperApp.class);
	}
	private void unmarshallSource() throws JAXBException {
		unmarshaller = jc.createUnmarshaller();
    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);  
	}
	private void mapPoms() throws JAXBException {    
		getSource().ifPresentOrElse(
				src -> {
					getMapper(src).ifPresent(
							m -> m.createPoms(XML_SOURCE));								
				}, 
				new Runnable() {					
					@Override
					public void run() {
						logger.error("Error getting the source [" + XML_SOURCE + "] for unmarshling");
					}
				});
	}
	private Optional<StreamSource> getSource() {
		//TODO - Check source file.
		return Optional.ofNullable(new StreamSource(XML_SOURCE));
	}
	private Optional<PomMapperApp> getMapper(StreamSource s){
		Optional<PomMapperApp> app = Optional.ofNullable(null);
		try {
			app = Optional.ofNullable(unmarshaller.unmarshal(s, PomMapperApp.class).getValue());			
		} catch (JAXBException e) {
			logger.error("Error unmarshalling source");
		}	 
		return app;
	}

	public void createClassFile() {
		// TODO Auto-generated method stub
		
	}
}
