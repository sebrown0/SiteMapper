/**
 * 
 */
package file.class_package;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class ClassPackage {
	protected String packagePath;
	
	protected void setPackagePath(String packagePath) {
		this.packagePath = packagePath + "\n\n";
	}

	@Override
	public String toString() {
		return packagePath;
	}
}
