@XmlSchema(namespace = app.Constants.ROOT_NAME_SPACE,
           xmlns = {@XmlNs(prefix = "",
                           namespaceURI = app.Constants.ROOT_NAME_SPACE)},
           elementFormDefault = XmlNsForm.QUALIFIED)
package app.xml_content;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;