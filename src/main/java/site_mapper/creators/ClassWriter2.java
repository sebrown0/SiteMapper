/**
 * 
 */
package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import file.imports.Import;
import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Write all the details necessary for the class, i.e.
 * 	its package
 * 	required imports
 * 	class body
 * 	constructor & super constructor
 * 	methods
 */
public class ClassWriter2 implements ClassWriterActions {
	private PackageHierarchy ph;	
	private String className;
	private BufferedWriter writer;
	private ComponentWriterSetter componentWriter;
	private ElementClass elementClass;
	private AnnotationWriter annotationWriter;
	Logger logger = LogManager.getLogger(ClassWriter2.class);
	
	public ClassWriter2(ElementClass elementClass, PackageHierarchy ph, BufferedWriter writer, ComponentWriterSetter componentWriter) {
		this.elementClass = elementClass;
		this.className = elementClass.getClassName();
		this.annotationWriter = new AnnotationWriter(writer, elementClass.getSiteMapInfo());
		this.ph = ph;
		this.writer = writer;
		this.componentWriter = componentWriter;
		this.componentWriter.setSiteMapInfo(elementClass.getSiteMapInfo());
	}

	public void writePackage() throws IOException {
		logger.debug("Writing package");
		writer.write("package " + ph.getHierarchyDotNotation() + ";");
		writeNewLines(2);		
	}
	public void writeImports() throws IOException {
		logger.debug("Writing imports");
		componentWriter.getImportNames().forEach(n -> {
			try {
				addImport(n);
			} catch (IOException e) {
				logger.error("Error writing imports");
			}
		});
		writeNewLine();		
	}
	
	private void addImport(Import imprt) throws IOException {
		writer.write(imprt.toString()); //CHANGED FROM ImportType.getPath();
		writeNewLine();
	}
//	private void addImport(ImportType type) throws IOException {
//		writer.write(type.getPath());
//		writeNewLine();
//	}
	
	public void writeComments() throws IOException {
		logger.debug("Writing comments");
		writer.write(Comments.getClassComments(elementClass.getSiteMapInfo()));
	}
	public void writeIndividualElements(ComponentWriter compWriter) throws IOException {
//		if(compWriter instanceof ComponentWriterVisitor ) {
//			logger.debug("Writing class specific components");
//			((ComponentWriterVisitor) compWriter)
//				.setElementClass(elementClass)
//				.setFileOutWriter(this)
//				.setSiteMapInfo(elementClass.getSiteMapInfo())
//				.writeComponents();		
//		}		
	}
	
	public void openClass(String type) throws IOException {
		if(type != null && type.length() > 0) {
			writer.write("\npublic class " + className + " extends " + type + " {");
		}else {
			writer.write("\npublic class " + className + " {");	
		}		
		writeNewLine();		
	}

	public void closeClass() throws IOException {
		writer.write("\n}");	
	}
	@Override //ClassWriterActions
	public void writeNewLines(int numLines) throws IOException {
		for (int i = 1; i <= numLines; i++) {
			writer.newLine();	
		}
	}
	@Override //ClassWriterActions
	public void writeNewLine() throws IOException {
		writer.newLine();
	}
	@Override //ClassWriterActions
	public void addTab() throws IOException {
		writer.write("\t");
	}
	@Override //ClassWriterActions
	public void writeValue(String value) throws IOException {
		writer.write(value);
	}
	@Override //ClassWriterActions
	public void writeLine(String value) throws IOException {
		writer.write(value);
		writer.newLine();
	}
	@Override //ClassWriterActions
	public void writeAnnotation() throws IOException {
		annotationWriter.writeAnnotation();
//		writer.write(
//				"\t@SiteMap(author=\"" + siteMapInfo.getAuthor() + "\"" + 
//				", version=\"" + siteMapInfo.getVersion() + "\"" + 
//				", date=\"" + timeStamp + "\")\n");
	}

}
