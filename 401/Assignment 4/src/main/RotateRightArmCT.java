package main;

import util.annotations.Tags;
@Tags({"rotateRightArm"})

public class RotateRightArmCT implements Common, WordInterface{
	
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