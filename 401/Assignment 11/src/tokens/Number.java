package tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Number Token"})
public class Number extends RegularTokens implements NumberInterface{

	int number;

	public int getNumber() {
		number = Integer.parseInt(value);
		return number;
	}
}
