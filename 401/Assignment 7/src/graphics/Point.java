package graphics;

import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Uses Cartesian representation.")
@Tags({"Locatable"})
public class Point extends Location{
	
	@Visible(false)
	public Point(int theX, int theY) {
		x = theX;
		y = theY;
	}

}
