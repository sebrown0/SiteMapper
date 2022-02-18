/**
 * 
 */
package helpers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class StringTests {

	@Test
	void replaceFwdSlashes() {
		assertEquals("one.two", StringUtils.replaceFwdSlashes("one/two", "."));		
	}

	@Test
	void camelCase() {
		assertEquals("oneTwo", StringUtils.camelCase("OneTwo"));		
	}
	
	@Test
	void pascalCase() {
		assertEquals("OneTwo", StringUtils.pascalCase("oneTwo"));		
	}

}
