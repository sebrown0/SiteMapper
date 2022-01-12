/**
 * 
 */
package file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Brown
 *
 */
public class FileReader <T extends Object>{
	private String root;
	private Class<T> clazz;
	private InputStream inputStream;
	
	public FileReader(String root, Class<T> clazz) {
		this.root = root;
		this.clazz = clazz;
		
		System.out.println(clazz.getSimpleName());

	}
	
	public FileReader<T> getInputStreamFromResources(String fileName) {		
		inputStream = clazz.getResourceAsStream(fileName);
		return this;
	}
	
	public String readFromInputStream() throws IOException {
		    StringBuilder resultStringBuilder = new StringBuilder();
		    try (BufferedReader br
		      = new BufferedReader(new InputStreamReader(inputStream))) {
		        String line;
		        while ((line = br.readLine()) != null) {
		            resultStringBuilder.append(line).append("\n");
		        }
		    }
		  return resultStringBuilder.toString();
		}
}
