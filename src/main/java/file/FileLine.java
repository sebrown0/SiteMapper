/**
 * 
 */
package file;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Inital
 * @since 1.0
 * 
 * A line in a file.
 * 
 */
public class FileLine {
	private int number;
	private String value;
	
	public FileLine(int number, String value) {
		this.number = number;
		this.value = value;
	}
	
	public int getNumber() {
		return number;
	}
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "FileLine [" + number + ": " + value + "]";
	}	
	
}
