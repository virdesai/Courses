package Main;

public class Bean {
	
	static String beanInput;

	public String getInput() {

		return Assignment2.input;

	}

	public void setInput(String newInput) {

		beanInput = newInput;
		
		Assignment2.analyzeAndPrintString(beanInput);

	}

}