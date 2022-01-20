/**
 * 
 */
package file.helpers;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Any element of a site-mapped file that is indented.
 */
public interface IndentedElement <T extends Object>  {
	T setIndent(int numTabs);
}
