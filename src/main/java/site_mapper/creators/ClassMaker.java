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

import file.class_file.ClassFile;
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
	private Logger logger = LogManager.getLogger(ClassMaker.class);
	
//	private ClassFile classFile;
	
	
	public ClassMaker(ElementClass elementClass, PackageHierarchy ph) {
		this.elementClass = elementClass;
		this.className = elementClass.getClassName();
		this.packageHierarchy = ph;
	}

	public void makeClass() {
//		String filePath = getFilePath();
//		ComponentWriterVisitor compWriter = 
//				(ComponentWriterVisitor) ZZZ_ClassComponentFactory.getComponentWriter(elementClass.getTypeName());
		
		if(elementClass.getSiteMapInfo().isOverwritingExisting()) {
			ClassFile classFile = 
					new ClassFile
						.NewClassFileBuilder(elementClass)//, compWriter)
						.build();
			
			ClassWriter classWriter = new ClassWriter(classFile);
			try {
				classWriter.writeClass();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			overwrite(filePath);
		}else {
			System.out.println("NOT OVER WRITTING"); // TODO - remove or log
			
			/*
			 * Have to diff the files
			 */
		}
		
	}
	
	private void overwrite(String filePath) {
		System.out.println("OVER WRITTING"); // TODO - remove or log
		try {
			fileOut = 				
					Optional.ofNullable(new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(filePath),	StandardCharsets.UTF_8)));
			
			fileOut.ifPresent(fileWriter -> {
				logger.info("Creating class [" + className + "].");
				
				ComponentWriterVisitor compWriter = 
						(ComponentWriterVisitor) ZZZ_ClassComponentFactory.getComponentWriter(elementClass.getTypeName());
				
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
		} catch (FileNotFoundException e) {
			logger.error("Error file output stream [" + filePath + "]");
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
