/**
 * 
 */
package helpers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import file.helpers.LocatorFactory;
import site_mapper.jaxb.pom.Locator;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class ByLocatorFactoryTests {

	@Test
	void invalidCss() {
		Locator loc = new Locator().setBy("cssss").setLocator("\"div[class='input-group']\"");
		assertEquals("", LocatorFactory.getByString(loc));
	}
	@Test
	void validCss() {
		Locator loc = new Locator().setBy("css").setLocator("\"div[class='input-group']\"");
		assertEquals("By.cssSelector(\"div[class='input-group']\")", LocatorFactory.getByString(loc));
	}
	@Test
	void validXpath() {
		Locator loc = new Locator().setBy("xpath").setLocator("../..");
		assertEquals("By.xpath(../..)", LocatorFactory.getByString(loc));
	}
	@Test
	void validId() {
		Locator loc = new Locator().setBy("id").setLocator("\"ID\"");
		assertEquals("By.id(\"ID\")", LocatorFactory.getByString(loc));
	}
}
