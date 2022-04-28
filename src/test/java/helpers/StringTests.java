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
		assertEquals("oneTwo", StringUtils.asCamelCase("OneTwo"));		
	}
	
	@Test
	void pascalCase() {
		assertEquals("OneTwo", StringUtils.asPascalCase("oneTwo"));		
	}

	@Test
	void removeUnderScores() {
		assertEquals("OneTwo", StringUtils.removeUnderScoresAndAsPascalCase("One_two"));
	}
	
	@Test
	void removeUnderScore_replaceWithSpace() {
		assertEquals("One Two", StringUtils.replaceUnderScoresWithSpaceAndAsPascalCase("One_two"));
	}

	@Test
	void removeSpace_asPascal() {
		assertEquals("OneTwo", StringUtils.removeSpacesAndAsPascalCase("One two"));
	}
	
	@Test
	void removeSpace_insertUnderScore_inLower() {
		assertEquals("one_two", StringUtils.replaceSpacesWithUnderScoreAndInLower("One Two"));
	}

}
