package tokens;

import main.ScanString;
import util.annotations.Tags;
@Tags({"move"})

public class MoveCT implements Common, WordInterface{
	
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