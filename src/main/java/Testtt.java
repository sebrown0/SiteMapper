
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import site_mapper.jaxb.pom.test.DataWrapper;

public class Testtt {

  public static void main(String[] args) throws Exception {
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
    properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
    JAXBContext jc = JAXBContext.newInstance(new Class[] {DataWrapper.class}, properties);

    Unmarshaller unmarshaller = jc.createUnmarshaller();
    StreamSource json = new StreamSource("./src/main/resources/input.json");
    DataWrapper dataWrapper = unmarshaller.unmarshal(json, DataWrapper.class).getValue();

    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(dataWrapper, System.out);
}

}


