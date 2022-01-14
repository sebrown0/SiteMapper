/**
 * 
 */
package file.comment;

import site_mapper.jaxb.pom.SiteMapInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class NewComment extends Comment {
	
	public NewComment(SiteMapInfo info) {
		createComment(info);
	}
	
	private void createComment(SiteMapInfo info) {
		super.lines
		.addLine("/**")
		.addLine("* Generated Class.")
		.addLine("* ----------------")
		.addLine("* Source:  " + info.getXmlSource())
		.addLine("* Author:  " + info.getAuthor())
		.addLine("* Version: " + info.getVersion())
		.addLine("* Created: " + info.getTimeStamp())
		.addLine("*/");
	}

	@Override
	public String toString() {
		String result = "";
		for (String s : lines.getLines()) {
			result += s + "\n";
		}
		return result;
	}	
}
