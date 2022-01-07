/**
 * 
 */
package site_mapper.jaxb.pom;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
@XmlRootElement(name="Info")
@XmlAccessorType(XmlAccessType.FIELD)
public class SiteMapInfo {
	@XmlElement(name="Author")
	private String author;
	@XmlElement(name="Version")
	private String version;
	@XmlElement(name="RootDir")	
	private String rootDir;
	@XmlElement(name="ParentPackage")
	private String parentPackage;

	private String xmlSource;
	private String date;
	private String time;
	
	public SiteMapInfo() {
		date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
		time = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now());
	}
	
	public SiteMapInfo setAuthor(String author) {
		this.author = author;
		return this;
	}
	public SiteMapInfo setVersion(String version) {
		this.version = version;
		return this;
	}
	public SiteMapInfo setXmlSource(String xmlSource) {
		this.xmlSource = xmlSource;
		return this;
	}
	
	public String getAuthor() {
		return author;
	}
	public String getVersion() {
		return version;
	}
	public String getXmlSource() {
		return xmlSource;
	}
	public String getDate() {
		return date;
	}
	public String getTime() {
		return time;
	}
	public String getTimeStamp() {
		return date + " " + time;
	}	
	public String getRootDir() {
		return rootDir;
	}
	public String getParentPackage() {
		return parentPackage;
	}

	@Override
	public String toString() {
		return "SiteMapInfo [author=" + author + ", version=" + version + ", rootDir=" + rootDir + ", parentPackage="
				+ parentPackage + ", xmlSource=" + xmlSource + ", date=" + date + ", time=" + time + "]";
	}
			
}
