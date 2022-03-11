package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import app.SiteMapContentGetter;
import app.xml_content.DynamicTestApp;
import app.xml_content.XmlContent;

class DeleteMe {
	private static final String XML_SOURCE = 
			"C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map/site_map.xml";

	
	@Test
	void test() {		
		SiteMapContentGetter<DynamicTestApp> getter = new SiteMapContentGetter<>(XML_SOURCE, DynamicTestApp.class);
		XmlContent content = getter.getContent().get();
		System.out.println("->"); // TODO - remove or log 	
	}

	@Test
	void jkjkjkkj() throws ParserConfigurationException, SAXException, FileNotFoundException, IOException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
    spf.setXIncludeAware(true);
    spf.setNamespaceAware(true);
//    try {
//    	
//			spf.setFeature("http://apache.org/xml/features/xinclude/fixup-base-uris", false);
//		} catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		SAXParser parser = spf.newSAXParser();
		XMLReader reader = parser.getXMLReader();
		MySaxHandler handler = new MySaxHandler();
		reader.setContentHandler(handler);
		reader.parse(
				new InputSource(
						new FileInputStream(
								new File(
										"C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/resources/site_map", 
										"site_map.xml"))));
		
	}
}
