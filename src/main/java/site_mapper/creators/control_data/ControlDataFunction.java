/**
 * 
 */
package site_mapper.creators.control_data;

import file.annotation.SiteMapAnnotation;
import file.helpers.Formatter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlDataFunction {
	private SiteMapAnnotation annotation;
	private String funcStr;
	
	public ControlDataFunction(SiteMapAnnotation annotation, String funcStr) {
		this.annotation = annotation;
		this.funcStr = funcStr;
	}
	
	public SiteMapAnnotation getAnnotation() {
		return annotation;
	}

	@Override
	public String toString() {		
		return String.format(
				"%s%s", 
				Formatter.getAnnotation(annotation),
				funcStr);
	}
	
}