/**
 * 
 */
package file;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Stage implements CurrentStage, NextStage {
	private Optional<FileStages> currentStage;
	
	public enum FileStages {
		INITIAL, 
		PACKAGE,
		IMPORTS,
		COMMENTS,
		OPEN_CLASS,
		VARIABLES,
		CONSTRUCTOR,
		BODY;
	}
	
	public Stage() {
		currentStage = Optional.of(FileStages.INITIAL);
	}
	
	private Queue<FileStages> stages = 
			new LinkedList<>(
					Arrays.asList(
							FileStages.INITIAL,
							FileStages.PACKAGE,
							FileStages.IMPORTS, 
							FileStages.COMMENTS, 
							FileStages.OPEN_CLASS, 
							FileStages.VARIABLES, 
							FileStages.CONSTRUCTOR, 
							FileStages.BODY)
					);
		
	/*
	 * if no stage is returned then at blank line.
	 */
	/*
	 * If a blank line is found:
	 * 	
	 */
	public Stage setStage(String lineData) {
		FileStages current = peekCurrent();

		if(isBlankLine(lineData)) {
			currentStage = Optional.ofNullable(null);
		}else if (current.equals(FileStages.VARIABLES)){	
		}else {

				if(lineData.startsWith("package")) {
					
				}else if (lineData.startsWith("import")) {
					
				}else if (lineData.startsWith("/") || lineData.startsWith("*")) {
					
				}else if (lineData.startsWith("public class")) {
					
				}else if (lineData.startsWith("@SiteMap")) {
					
				}			
			
		}
		
		return this;
	}
	
	private boolean isBlankLine(String lineData) {
		return (lineData.length()>0) ? false : true;
	}
	
	public Stage setStage(FileStages stage) {
		while(!stages.peek().equals(stage)) {
			stages.poll();			
		}
		return this;
	}
	
	@Override // NextStage
	public FileStages moveNext() {
		stages.poll();
		return peekCurrent();
	}
	@Override // CurrentStage
	public FileStages peekCurrent() {
		return stages.peek();
	}
	
}
