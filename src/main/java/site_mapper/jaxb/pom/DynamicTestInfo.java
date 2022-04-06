/**
 * 
 */
package site_mapper.jaxb.pom;



import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="DynamicTestInfo")
public class DynamicTestInfo {
	@XmlElement(name="ReportStrategy", namespace="DynamicTestInfo")
	private String reportStrategy;
	@XmlElement(name="ReportResults", namespace="DynamicTestInfo")
	private String reportResults;
	public String getReportStrategy() {
		return reportStrategy;
	}
	public String getReportResults() {
		return reportResults;
	}
	public void setReportStrategy(String reportStrategy) {
		this.reportStrategy = reportStrategy;
	}
	public void setReportResults(String reportResults) {
		this.reportResults = reportResults;
	}
	
}
