package tests;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySaxHandler extends DefaultHandler{
	private boolean isInfo=false;
	private StringBuilder elementValue;
	
	@Override
  public void characters(char[] ch, int start, int length) throws SAXException {
		if(isInfo) {
			if (elementValue == null) {
	      elementValue = new StringBuilder();
		  } else {
		      elementValue.append(ch, start, length);
		  }	
		}
    
//      System.out.println("\ncharacters"); // TODO - remove or log
//      for(int i=0; i<length; i++) {
//      	System.out.print(ch[i]); // TODO - remove or log	
//      }       	
  }

  @Override
  public void startDocument() throws SAXException {
//  	System.out.println("startDocument"); // TODO - remove or log
  }

  @Override
  public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
  	System.out.println(qName ); // TODO - remove or log 	
  	if(qName.equalsIgnoreCase("Info")) {
  		isInfo=true;  			
  	}else {
  		isInfo=false;
  	}
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
  	if(isInfo) {
  		System.out.println(elementValue.toString()); // TODO - remove or log 	
  	}
//  	System.out.println("endElement"); // TODO - remove or log
  }


}
