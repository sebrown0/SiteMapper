/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import site_mapper.creators.navigation.left_menu.LeftMenuElementCreator;
import site_mapper.creators.navigation.left_menu.LeftMenuElementFactoryUpdater;
import site_mapper.jaxb.pom.PackageHierarchy;
import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class LeftMenuElementFactoryUpdater_Tests {	
	private static LeftMenuElementCreator creator = new LeftMenuElementCreator(null);
		
	@BeforeAll
	public static void setup() {
		SiteMapInfo info = 
			new SiteMapInfo()
				.setRootDir("src/test/resources")
				.setParentPackage("library/object_models/modules");
		
		PackageHierarchy ph = new PackageHierarchy(info);		
		creator.setModuleName("payroll").setPackageHierarchy(ph).setSiteMapInfo(info);		
	}
	
	@Test
	void test_existingChild() {
		Map<String, List<String>> parents = new HashMap<>();
		parents.put("Employees", Arrays.asList("Contact Numbers"));		
		LeftMenuElementFactoryUpdater updater = new LeftMenuElementFactoryUpdater(creator, parents, null);
		
		assertTrue(checkOccurances("ContactNumbers.MENU_TITLE:", updater.updateFactoryContents())==1);
	}
	
	@Test
	void test_newChildren_forExistingParent() {
		Map<String, List<String>> parents = new HashMap<>();
		parents.put("Employee Others", Arrays.asList("Absence Entitlements", "Loans"));		
		LeftMenuElementFactoryUpdater updater = new LeftMenuElementFactoryUpdater(creator, parents, null);
		
		assertTrue(checkContents("AbsenceEntitlements.MENU_TITLE:", updater.updateFactoryContents()));
	}
	
	@Test
	void test_newParent() {
		Map<String, List<String>> parents = new HashMap<>();
		parents.put("Payroll", Arrays.asList("Payroll Details DrillDown"));		
		LeftMenuElementFactoryUpdater updater = new LeftMenuElementFactoryUpdater(creator, parents, null);
		
		assertTrue(checkContents("PayrollDetailsDrillDown.MENU_TITLE:", updater.updateFactoryContents()));
	}
	
	@Test
	void test_newStandAlone() {
		List<String> standAlone = new ArrayList<>(Arrays.asList("Documents"));				
		LeftMenuElementFactoryUpdater updater = new LeftMenuElementFactoryUpdater(creator, null, standAlone);
		
		assertTrue(checkContents("Documents.MENU_TITLE:", updater.updateFactoryContents()));
	}
	
	@Test
	void test_newStandAlone_and_newParent_and_existingParent() {
		Map<String, List<String>> parents = new HashMap<>();
		parents.put("Payroll", Arrays.asList("Payroll Details DrillDown"));
		parents.put("Employees", Arrays.asList("Contact Numbers"));		
		
		List<String> standAlone = new ArrayList<>(Arrays.asList("Documents","EmployeeList"));
		LeftMenuElementFactoryUpdater updater = new LeftMenuElementFactoryUpdater(creator, parents, standAlone);
		
		List<String> content = updater.updateFactoryContents();
		
		assertTrue(checkContents("Documents.MENU_TITLE:", content));
		assertTrue(checkContents("PayrollDetailsDrillDown.MENU_TITLE:", updater.updateFactoryContents()));
		assertTrue(checkOccurances("ContactNumbers.MENU_TITLE:", content)==1);
		assertTrue(checkOccurances("EmployeeList.MENU_TITLE:", content)==1);
	}
	
	private int checkOccurances(String forStr, List<String> content) {
		int res = 0;
		for(String str : content) {
			if(str.contains(forStr)) {
				res++;
			}
		}
		return res;			
	}
	
	private boolean checkContents(String forStr, List<String> content) {
		boolean res = false;
		for(String str : content) {
			if(str.contains(forStr)) {
				res = true;
				break;
			}
		}
		return res;			
	}

}
