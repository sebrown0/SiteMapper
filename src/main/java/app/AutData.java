/**
 * 
 */
package app;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * The data required by SiteMapper to 
 * create the POMs for an AUT.
 * 
 */
public class AutData implements AutDataGetter {
	private String rootDir;
	private String xmlSource;
	
	public AutData setRootDir(String rootDir) {
		this.rootDir = rootDir;
		return this;
	}
	public AutData setXmlSource(String xmlSource) {
		this.xmlSource = xmlSource;
		return this;
	}
	
	@Override
	public String getRootDir() {
		return rootDir;
	}
	@Override
	public String getXmlSource() {
		return xmlSource;
	}	
	
}
