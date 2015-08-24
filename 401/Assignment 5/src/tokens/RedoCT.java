package tokens;

import main.ScanString;
import util.annotations.Tags;
@Tags({"redo"})

public class RedoCT implements Common, WordInterface{
	
	String word;
	
	public String getInput() {

		return ScanString.mainInput;

	}
	
	public void setInput(String newInput) {

		word = newInput.toLowerCase();

	}
	
	public String getWord() {

		return word;

	}

}