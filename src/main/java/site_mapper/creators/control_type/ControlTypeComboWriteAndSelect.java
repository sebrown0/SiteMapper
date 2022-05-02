/**
 * 
 */
package site_mapper.creators.control_type;

import java.util.Arrays;
import java.util.List;

import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlTypeComboWriteAndSelect extends ControlType implements RequiresImports {

	public ControlTypeComboWriteAndSelect(ElementCreation element) {
		super(element, "cws");
	}
	
	@Override
	public String getControlDataString() {		
		return 
				String.format(
						"\t\tControlGetter %s =" +
						"\n\t\t\tnew ControlGetterComboWriteAndSelect(" +
						"\n\t\t\t\t\"%s\", coreData" +
						"\n\t\t\t\t%s," +
						"\n\t\t\t\tBy.className(\"select2-results\")," +
						"\n\t\t\t\tnew TextWriterComboMulti(coreData, getContainer()));",
						nameWithAcronym, nameWithAcronym, getLocators());
	}

	@Override
	public List<String> getRequiredImports() {
		return Arrays.asList("TextWriterComboMulti");
	}

}
