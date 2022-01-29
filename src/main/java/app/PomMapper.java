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
public class PomMapper {
	private Logger logger = LogManager.getLogger(this.getClass());
	private JAXBContext jc;
	private Unmarshaller unmarshaller;
	
	private Optional<PomMapperApp> content = Optional.empty();
	
	private final String XML_SOURCE;
	
	public PomMapper(final String XML_SOURCE) {
		this.XML_SOURCE = XML_SOURCE;	
	}
	

	public Optional<PomMapperApp> getContent() {

		try {
			setJaxContext();
			unmarshallSource();
			getSource().ifPresentOrElse(
					src -> {
						content = getMapperNew(src);								
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

	private Optional<PomMapperApp> getMapperNew(StreamSource s){
		Optional<PomMapperApp> app = Optional.ofNullable(null);
		try {
			app = Optional.ofNullable(unmarshaller.unmarshal(s, PomMapperApp.class).getValue());			
		} catch (JAXBException e) {
			System.out.println(e);
			logger.error("Error unmarshalling source");
		}	 
		return app;
	}

	
	//*****************************************************************

	private Optional<PomMapperApp> getMapper(StreamSource s){
		Optional<PomMapperApp> app = Optional.ofNullable(null);
		try {
			app = Optional.ofNullable(unmarshaller.unmarshal(s, PomMapperApp.class).getValue());			
		} catch (JAXBException e) {
			logger.error("Error unmarshalling source");
		}	 
		return app;
	}
	public void  createProdPoms() {
		writeLogHeader();
		try {
			setJaxContext();
			unmarshallSource();
			mapProdPoms();
		} catch (JAXBException e) {
			logger.error("Could not create JAXB context. Quitting");
		}
	}
	
	public void  createTestPoms(PomMapperVisitor visitor) {
		writeLogHeader();
		try {
			setJaxContext();
			unmarshallSource();
			mapTestPoms(visitor);
		} catch (JAXBException e) {
			logger.error("Could not create JAXB context. Quitting");
		}
	}
	//*****************************************************************
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
	private void mapProdPoms() throws JAXBException {    
		getSource().ifPresentOrElse(
				src -> {
//					getMapper(src).ifPresent(
//							m -> m.createProdPoms(XML_SOURCE));								
				}, 
				new Runnable() {					
					@Override
					public void run() {
						logger.error("Error getting the source [" + XML_SOURCE + "] for unmarshling");
					}
				});
	}
	private void mapTestPoms(PomMapperVisitor visitor) throws JAXBException {    
		getSource().ifPresentOrElse(
				src -> {
//					getMapper(src).ifPresent(
//							m -> m.createTestPoms(XML_SOURCE, visitor));								
				}, 
				new Runnable() {					
					@Override
					public void run() {
						logger.error("Error getting the source [" + XML_SOURCE + "] for unmarshling");
					}
				});
	}


}
