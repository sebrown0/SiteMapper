/**
 * 
 */
package file.variable;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Intial
 * @since 1.0
 * 
 * Abstract POJO for variables in SiteMap class file.
 */

public abstract class Variable implements VariableSetter {
	protected String modifier;
	protected String type;
	protected String name;
	protected String value;
		
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
	@Override // VariableSetter
	public VariableSetter setValue(String value) {
		this.value = value;
		return this;
	}
		
}
