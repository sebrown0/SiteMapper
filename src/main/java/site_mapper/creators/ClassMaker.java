package site_mapper.creators;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import site_mapper.elements.ElementClass;
import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Direct the creation of each required
 * element of the class.
 * 
 */
public class ClassMaker {
	private ElementClass elementClass;
	private PackageHierarchy packageHierarchy;
	private String className;
	private Optional<BufferedWriter> fileOut;
	Logger logger = LogManager.getLogger(ClassMaker.class);
	
	public ClassMaker(ElementClass elementClass, PackageHierarchy ph) {
		this.elementClass = elementClass;
		this.className = elementClass.getClassName();
		this.packageHierarchy = ph;
	}

	public void makeClass() {
		setWriter();
		fileOut.ifPresent(fileWriter -> {
			logger.info("Creating class [" + className + "].");
			
			ComponentWriterVisitor compWriter = 
					(ComponentWriterVisitor) ClassComponentFactory.getComponentWriter(elementClass.getTypeName());
			
			ClassWriter classWriter = 
					new ClassWriter(elementClass, packageHierarchy, fileWriter, compWriter);
				
			try {				
				classWriter.writePackage();
				classWriter.writeImports();
				classWriter.writeComments();
				classWriter.openClass(elementClass.getTypeName());				
				// Write elements specific to the class. 
				classWriter.writeIndividualElements(compWriter);				
				classWriter.closeClass();				
			} catch (IOException e) {
				logger.error("Error creating class [" + className + "]");
			}
			closeFile();
		});
	}
	
	private void setWriter(){
		String filePath = getFilePath();
		//filePath	"C:/Users/SteveBrown/eclipse-workspace/2021/SiteMapper/src/main/java/a/payroll/Left/employees/EmployeeDetails.java" (id=104)	
		if(elementClass.getSiteMapInfo().isOverwritingExisting()) {
			System.out.println("OVER WRITTING"); // TODO - remove or log
			try {
				fileOut = 				
						Optional.ofNullable(new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(filePath),	StandardCharsets.UTF_8)));
			} catch (FileNotFoundException e) {
				logger.error("Error file output stream [" + filePath + "]");
			}	
		}else {
			System.out.println("NOT OVER WRITTING"); // TODO - remove or log 	
			/*
			 * Have to diff the files
			 */
		}
		
	}
	private String getFilePath() {
		return 
				packageHierarchy.getRoot() + "/" + 
				packageHierarchy.getHierarchyFwdSlashNotation() + "/" + 
				className + ".java";
	}
	
	private void closeFile() {
		fileOut.ifPresent(w -> {
			try {
				w.close();
			} catch (IOException e) {
				logger.error("Error closing file");
			}
		});
	}

}
