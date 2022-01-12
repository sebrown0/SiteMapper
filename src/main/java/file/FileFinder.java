/**
 * 
 */
package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class FileFinder {
	private static Optional<String> filePath;
	private static String result;
	private static Logger logger = LogManager.getLogger(FileFinder.class);
	
	public static boolean fileExists(final String ROOT, final String fileName) {
		try {
			return (findFilePath(ROOT, fileName).isPresent()) ? true : false;
		}catch(Exception e) {
			return false;
		}
	}
	
	public static String findPathWithoutRootAndExtension(final String ROOT, final String fileName) {
		result = findPathWithoutRoot(ROOT, fileName);
		if(result.length() > 0) {
			int pos = result.indexOf(".");
			result = result.substring(0, pos);
		}
		return result;
	}
	
	public static String findPathWithoutRoot(final String ROOT, final String fileName) {
		result = "";
		findFilePath(ROOT, fileName).ifPresent(fp ->{
			result = fp.substring(ROOT.length() + 1);
		});		
		return result;
	}
	
	public static String findPathWithRoot(final String ROOT, final String fileName) {
		result = "";
		findFilePath(ROOT, fileName).ifPresent(fp ->{
			result = fp;
		});		
		return result;
	}
	
	private static Optional<String> findFilePath(final String ROOT, final String fileName){
		filePath = Optional.ofNullable(null);
		try (Stream<Path> walkStream = Files.walk(Paths.get(ROOT))) {
	    walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
	      if (f.toString().endsWith("\\" + fileName)) {
	      	filePath = Optional.ofNullable(f.toString());
	      }
	    });
		} catch (IOException e) {
			logger.error("Could not get file path for [ROOT=" + ROOT + "], [FILE=" + fileName + "]");
		}
		return filePath;
	}
	
}
