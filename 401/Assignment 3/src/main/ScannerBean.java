package main;

public class ScannerBean {
	
	static String beanInput;

	public String getInput() {

		return Assignment3.input;

	}

	public void setInput(String newInput) {

		beanInput = newInput;
		
		ScanString.analyzeAndPrintString(beanInput);

	}

}
