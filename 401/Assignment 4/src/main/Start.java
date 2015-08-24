package main;

import util.annotations.Tags;
@Tags({"Start Token"})

public class Start implements Common{

	String start;

	public String getInput() {

		return start;

	}
	
	public void setInput(String newInput) {

		start = String.valueOf(newInput);

	}

}
