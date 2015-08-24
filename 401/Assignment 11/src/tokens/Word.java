package tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Word Token"})
public class Word extends RegularTokens implements WordInterface{
	
	String word;

	public String getWord() {
		word = value.toLowerCase();
		return word;
	}
}