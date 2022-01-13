/**
 * 
 */
package file.variable;

import file.SiteMapAnnotation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Intial
 * @since 1.0
 * 
 * Abstract POJO for variables in SiteMap class file.
 */

public abstract class Variable implements VariableSetter {
	protected SiteMapAnnotation annotation;
	protected String modifier;
	protected String name;
		
	@Override // VariableSetter
	public VariableSetter setAnnotation(SiteMapAnnotation annotation) {
		this.annotation = annotation;
		return this;
	}
	@Override // VariableSetter
	public VariableSetter setModifier(String modifier) {
		this.modifier = modifier;
		return this;
	}
	@Override // VariableSetter
	public VariableSetter setName(String name) {
		this.name = name;
		return this;
	}
		
}
