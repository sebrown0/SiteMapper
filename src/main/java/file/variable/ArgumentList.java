/**
 * 
 */
package file.variable;

import org.apache.logging.log4j.LogManager;

import file.helpers.Lines;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ArgumentList {
	private Lines<Argument> args = new Lines<>();
	
	public void addArg(Argument arg) {
		if(arg != null) {
			args.addLine(arg);
		}
	}
	
	public ArgumentList createArgList(String ofArgs) {
		if(ofArgs != null) {
			String[] allArgs = ofArgs.split(",");
			if(allArgs != null && allArgs.length > 0) {				
				for(int idx=0; idx < allArgs.length; idx++) {
					String[] argParts = allArgs[idx].trim().split(" ");
					if(argParts != null && argParts.length == 2) {						
						Argument arg = new Argument(argParts[0], argParts[1]);
						args.addLine(arg);
					}else {
						logBadArg("Bad argument found in list", ofArgs);
					}
				}
			}else {
				logBadArg("Error splitting list", ofArgs);
			}
		}else {
			logBadArg("Cannot create args from null list", ofArgs);
		}
		return this;
	}
	
	private void logBadArg(String msg, String ofArgs) {
		System.out.println(msg + " [" + ofArgs +"]"); // TODO - remove or log 	
		LogManager.getLogger().error(msg + " [" + ofArgs +"]");
	}
	
	@Override
	public String toString() {
		String res = "";
		boolean firstPass = true;
		
		for (Argument a : args.getLines()) {
			if(firstPass) {
				firstPass = false;
				res += a.toString();
			}else {
				res += ", " + a.toString();	
			}			
		}
		return res;
	}
}

