package tokens;

import util.annotations.Tags;
@Tags({"End Token"})

public class End implements Common{
	
	String end;
	
	public String getInput() {

		return end;

	}
	
	public void setInput(String newInput) {

		end = String.valueOf(newInput);

	}

}
