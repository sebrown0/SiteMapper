/**
 * 
 */
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
import site_mapper.jaxb.pom.PackageHierarchy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Write the given class file.
 * 
 */
public class ClassWriter {
	private static Logger logger = LogManager.getLogger(ClassWriter.class);	
	private Optional<BufferedWriter> fileOut;
	private PackageHierarchy packageHierarchy;
	private ClassFile clazz;
	
	public ClassWriter(ClassFile clazz, PackageHierarchy packageHierarchy) {
		this.packageHierarchy = packageHierarchy;
		this.clazz = clazz;
	}

	public void writeClass() {
		setFileOutWriter();
		writeFile();
		closeFile();
	}
	
	private void setFileOutWriter() {
		String filePath = getFilePath();
		try {
			fileOut = 				
					Optional.ofNullable(new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(filePath),	StandardCharsets.UTF_8)));
		} catch (FileNotFoundException e) {
			logger.error("Error file output stream [" + filePath + "]");
		}
	}

	private String getFilePath() {
		return 
				packageHierarchy.getRoot() + "/" + 
				packageHierarchy.getHierarchyFwdSlashNotation() + "/" + 
				clazz.getClassName() + ".java";
	}
	
	private void writeFile() {
		fileOut.ifPresent(fileWriter -> {
			logger.info("Creating class [" + clazz.getClassName() + "].");
			try {
				fileWriter.write(clazz.toString());
			} catch (IOException e) {
				logger.error("Error writing to file");
			}
		});
	}
	
	private void closeFile() {
		fileOut.ifPresent(f -> {
			try {
				f.close();
			} catch (IOException e) {
				logger.error("Error closing file");
			}
		});
	}
	
}
