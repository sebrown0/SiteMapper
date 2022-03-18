/**
 * 
 */
package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class FileWriter {
	private static Optional<BufferedWriter> fileOut;		
	private static final Logger LOGGER = LogManager.getLogger(FileWriter.class);	
	private static final String ROOT = "C:/Users/SteveBrown/eclipse-workspace/2021/DTest/src/main/java/";
	
	//Dir may not be created so create it first.
	public static void writeFile(Object toWrite, String filePath, String fileName) {
		new File(ROOT + filePath).mkdirs();
		writeFile(toWrite, filePath + "/" + fileName);
	}
	
	//The dir is already created, so filePath is the full path including the file name.
	public static void writeFile(Object toWrite, String filePath) {
		setFileOutWriter(filePath);
		writeFile(toWrite);
		closeFile();
	}
	
	private static void setFileOutWriter(String filePath) {
		try {
			fileOut = 				
					Optional.ofNullable(new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(filePath),	StandardCharsets.UTF_8)));
		} catch (FileNotFoundException e) {
			LOGGER.error("Error file output stream [" + filePath + "]");
		}
	}
	
	private static void writeFile(Object toWrite) {
		fileOut.ifPresent(fileWriter -> {
			try {
				fileWriter.write(toWrite.toString());
			} catch (IOException e) {				
				LOGGER.error("Error writing to file");
			}
		});
	}
	
	private static void closeFile() {
		fileOut.ifPresent(f -> {
			try {
				f.close();
			} catch (IOException e) {
				LOGGER.error("Error closing file");
			}
		});
	}
	
}
