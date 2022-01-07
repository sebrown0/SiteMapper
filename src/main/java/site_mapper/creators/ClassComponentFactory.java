/**
 * 
 */
package site_mapper.creators;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ClassComponentFactory {
	public static ComponentWriter getComponentWriter(String typeName) {
		ComponentWriter cw = switch (typeName) {
			case "JsPanelWithIFrame" -> new ComponentWriterJsPanelWithIFrame();
			default -> throw new IllegalArgumentException("Unexpected value: " + typeName);
		};
		return cw;
	}
}
