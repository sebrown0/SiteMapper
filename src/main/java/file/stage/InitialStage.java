/**
 * 
 */
package file.stage;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class InitialStage extends ExistingStage{

	@Override
	public Stage getNext() {
		return new PackageStage();
	}

}
