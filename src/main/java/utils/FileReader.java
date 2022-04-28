/**
 * 
 */
package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class FileReader {
	private static List<String> content;
	
	public static List<String> getFileAsList(Optional<String> filePath){
		filePath.ifPresent(f -> {
			content = new ArrayList<>();
			try {
				Scanner fileScanner = new Scanner(new FileInputStream(f));
				while(fileScanner.hasNextLine()) {
					content.add(fileScanner.nextLine());					
				}
			} catch (FileNotFoundException e) {
				LogManager.getLogger().error("File [" + f + "] not found, so cannot read it");
			}
		});				
		return content;
	}
	
}
