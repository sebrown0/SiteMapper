/**
 * 
 */
package site_mapper.creators;

import file.class_file.ClassFile;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface ClassMaker {
	ClassFile makeClassFile();
	String getCreationModeStr();
}
