package main;

public class Number implements Common, NumberInterface{

	int number;

	public String getInput() {

		return ScanString.mainInput;

	}

	public void setInput(String newInput) {

		number = Integer.parseInt(newInput);

	}
	
	public int getNumber() {
		
		return number;
		
	}

}
