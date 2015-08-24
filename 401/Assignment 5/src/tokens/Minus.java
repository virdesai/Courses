package tokens;

import util.annotations.Tags;
@Tags({"Minus Token"})

public class Minus implements Common{
	
	String minus;
	
	public String getInput() {

		return minus;

	}
	
	public void setInput(String newInput) {

		minus = String.valueOf(newInput);

	}

}
