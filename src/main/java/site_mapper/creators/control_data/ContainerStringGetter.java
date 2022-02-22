/**
 * 
 */
package site_mapper.creators.control_data;

import java.util.List;
import java.util.stream.Collectors;

import file.helpers.Formatter;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.pom.Element;
import utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ContainerStringGetter {
	private static String name = "";
	private static String grpStr = "";
	private static String elementsStr = "";
	
	public static String getStringFrom(Container c) {
		setGroupString(c);
		setElements(c);
		return String.format("\t\t/* Controls in group[%s] */\n%s\t\t/* Control group [%s] */\n%s", name, elementsStr, name, grpStr);
	}
	
	private static void setElements(Container c) {
		List<Element> elements = c.getElements();
		if(elements != null) {
			elements.forEach(e -> {
				elementsStr += e.getElementString() + "\n";
			});
		}
	}
	
	private static void setGroupString(Container c) {
		name = c.getName();
		final String type = c.getType();
		final String loc = c.getLocator().toString();
		
		grpStr = 
			String.format(
					"\t\tControlGetterGroup %s =\n\t\t\tnew ControlGetter%s(\"%s\", coreData, %s\n\t\t\t\t.addControls(Arrays.asList(%s));", 
					StringUtils.camelCase(name), 
					StringUtils.pascalCase(type), 
					StringUtils.pascalCase(name), 
					loc,
					elementsAsCommaSepList(c.getElements()));
	}
	
	private static String elementsAsCommaSepList(List<Element> els) {
		List<String> 
			elNames = els.stream()
				.map(e -> e.getElementName())
				.collect(Collectors.toList()); 
		
		return Formatter.getAsCommaSeparatedList(elNames); 			
	}
}
