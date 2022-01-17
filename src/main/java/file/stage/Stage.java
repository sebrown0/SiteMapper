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
public interface Stage {
	List<String> getLines();
	Stage getNext();
}
