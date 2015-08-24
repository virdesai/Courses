package tokens;

import main.ScanString;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Number Token"})
public class Number implements NumberInterface{

	int number;

	public String getInput() {
		return ScanString.sendInput;
	}

	public void setInput(String newInput) {
		number = Integer.parseInt(newInput);
	}
	
	public int getNumber() {
		return number;
	}

}
