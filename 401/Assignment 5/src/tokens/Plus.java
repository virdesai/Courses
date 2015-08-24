package tokens;

import util.annotations.Tags;
@Tags({"Plus Token"})

public class Plus implements Common{
	
	String plus;
	
	public String getInput() {

		return plus;

	}
	
	public void setInput(String newInput) {

		plus = String.valueOf(newInput);

	}

}
