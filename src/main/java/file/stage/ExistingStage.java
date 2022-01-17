/**
 * 
 */
package file.stage;

import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class ExistingStage implements Stage {
	protected List<String> lines;	

	@Override
	public List<String> getLines() {
		return lines;
	}
}
