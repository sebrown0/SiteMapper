/**
 * 
 */
package file.class_file;

import java.util.List;

import file.annotation.ExistingAnnotation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ExistingConstructor extends ClassConstructor {
	private List<String> lines;

	public ExistingConstructor(List<String> lines) {
		this.lines = lines;
	}
	
	public ExistingConstructor setAnnotation(ExistingAnnotation cnstrAnnotation) {
		super.cnstrAnnotation = cnstrAnnotation;
		return this;
	}
	
	public void mapConstructor() {
		for (String s : lines) {
			System.out.println(s + "\n"); // TODO - remove or log 	
		}
	}
	
	
}
