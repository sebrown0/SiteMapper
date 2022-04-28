/**
 * 
 */
package library.left_menu;

import org.apache.logging.log4j.LogManager;

import core_data.CoreData;
import library.common.forms.ContainerAction;
import library.helpers.payroll_initialise.InitialisePayroll;
import library.object_models.modules.payroll.left_menu.Documents;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Intial
 * @since 1.0
 *
 */
public class PayrollLeftMenuElementFactory implements MenuElementFactory {	
	private CoreData coreData;
		
	public PayrollLeftMenuElementFactory(CoreData coreData) {
		this.coreData = coreData;
	}

	@Override // MenuElementFactory
	public ContainerAction getElementForName(String elementName) {
		
		ContainerAction element = null;
		switch (elementName) {		
			
		/* Employees */		
		case EmployeeDetails.MENU_TITLE:
			element = new EmployeeDetails(coreData);
			break;			
		case ContactNumbers.MENU_TITLE:
			element = new ContactNumbers(coreData);
			break;
		
		/* Employee Others */
		case TaxArrears.MENU_TITLE:
			element = new TaxArrears(coreData);
			break;
		
		/* Stand Alone */
		case EmployeeList.MENU_TITLE:
			element = new EmployeeList(coreData);
			break;
			
		default:
			LogManager.getLogger().error("Could not create [" + elementName + "]");				
			break;
			
		return element;
	}

}