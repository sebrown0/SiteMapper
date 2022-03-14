/**
 * 
 */
package file.class_file.body;

import file.annotation.NewAnnotation;
import file.annotation.SiteMapAnnotation;
import site_mapper.jaxb.pom.ElementFunction;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DynamicTestMethodBuilder {
	private ElementFunction func;
	private SiteMapInfo info;
	private SiteMapAnnotation anno;
	
	public DynamicTestMethodBuilder(ElementFunction func, SiteMapInfo info) {
		this.func = func;		
		this.info = info;
	}
	
	public DynamicTestMethodBuilder(ElementFunction func, SiteMapAnnotation anno) {
		this.func = func;		
		this.anno = anno;
	}
	
	public String build() {
		String res = "";
		if(anno != null) {
			res = String.format("\n%s%s", anno.toString(), func.toString());
		}else if (info != null) {
			res = String.format("\n%s%s", new NewAnnotation(info, 1), func.toString());
		}else {
//			TOOD - ERROR
		}
		return res;
	}

}
