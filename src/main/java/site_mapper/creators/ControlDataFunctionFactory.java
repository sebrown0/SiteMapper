/**
 * 
 */
package site_mapper.creators;

import java.util.List;
import java.util.Optional;

import exceptions.InvalidArgumentException;
import file.annotation.SiteMapAnnotation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get a string that can be added to a 
 * class file as the java code for creating
 * a new ControlData object, i.e.
 * 	"new ControlData(
 * 		"control name", 
 * 		new ControlGetterTextOut(coreData, By.id("FORM_ID")))"
 * 
 */
public class ControlDataFunctionFactory {
	private List<ControlDataValues> values;
	private String func = "";
	private int numControls;
	private SiteMapAnnotation anno;
	
	public ControlDataFunctionFactory(List<ControlDataValues> values, SiteMapAnnotation anno) {
		this.values = values;
		this.anno = anno;
	}
	
	public ControlDataFunction getFunctionBuildMyControls() throws InvalidArgumentException {
		if(values != null && values.size() > 0) {
			numControls = values.size();		
			func = 
				"\tprivate void buildMyControls(){\n" +
				"\t\tvar myControls =\n" +
				"\t\t\tList.of(";
		
			for (ControlDataValues v : values) {
				numControls--;
				getControlData(v).ifPresent(s -> {
					addControlToFunction(s);
				});
			}			
			func += "\n\t\t\t);\n\t\tsuper.buildPanelControls(myControls);\n\t}";		
		}else {
			func = "\t\tprivate void buildMyControls() {}";
		}	
		return new ControlDataFunction(anno, func);
	}
	private void addControlToFunction(String cntrlString) {
		if(numControls > 0) {
			func += "\n\t\t\t\t" + cntrlString + ",";	
		}else {
			func += "\n\t\t\t\t" + cntrlString;
		}		
	}

	/**
	 * @param controlTypeName
	 * 	button, text box etc
	 * @param byValue
	 * 	the value to be used to find the element in the DOM, 'By'.
	 * @param byType
	 * 	the type of 'By', i.e. CSS, XPATH or ID.
	 * @return: 
	 * 	a string to create a ControlData object
	 * @throws InvalidArgumentException 
	 * 
	 * This is a public method only because we want to test it in isolation.
	 */
	public static Optional<String> getControlData(ControlDataValues values)	throws InvalidArgumentException {		
		String cd = null;
		Optional<String> actualType = checkByType(values.getByType());
		Optional<String> controlTypeName = Optional.ofNullable(values.getControlTypeName());
		if(actualType.isPresent()) {
			if(controlTypeName.isPresent()) {
				cd = switch (controlTypeName.get()) {
					case "button" -> getControlDataStr(values.getControlName(), "ControlGetterButton", values.getByValue(), actualType.get());
					case "text_out" -> getControlDataStr(values.getControlName(), "ControlGetterTextOut", values.getByValue(), actualType.get());
					default -> throw new InvalidArgumentException("[" + values.getControlTypeName() + "] is not a valid control type name.");
				};	
			}else {
				throw new InvalidArgumentException("[" + values.getControlTypeName() + "] is not a valid control type name.");
			}
		}else {
			throw new InvalidArgumentException("[" + values.getByType() + "] is not a valid By type name.");
		}					
		return Optional.ofNullable(cd);
	}

	private static Optional<String> checkByType(String byType) {
		String ret = null;
		if(byType != null) {
			if(byType.equalsIgnoreCase("CSS")) {
				ret = "By.cssSelector";
			}else if(byType.equalsIgnoreCase("XPATH")){
				ret = "By.xpath";
			}else if(byType.equalsIgnoreCase("ID") ) {
				ret = "By.id";
			}	
		}		
		return Optional.ofNullable(ret);
	}

	private static String getControlDataStr(String controlName, String controlGetter, String byValue, String byActualType) {
		String str = 
				"new ControlData(" + 
				"\"" + controlName + "\", new " +
				controlGetter + "(coreData, " +
				byActualType +"(" +
				byValue;

		return str += ")))";
	}
}
