package tokens;

import util.annotations.Tags;
@Tags({"Quote Token"})

public class Quote implements Common{
	
	String quote;
	
	public String getInput() {

		return quote;

	}
	
	public void setInput(String newInput) {
		
		quote = String.valueOf(newInput);
		
	}

}
