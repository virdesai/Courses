package tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Regular Tokens"})
public abstract class RegularTokens implements Common{
	
	String value;
	
	public String getInput() {
		return value;
	}

	public void setInput(String newInput) {
		value = String.valueOf(newInput);
	}
}
