package graphics;

import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
@Tags({"Locatable"})
public interface LocationInterface {
	public int getX(); 
	public int getY(); 
	public void setX(int newX);
	public void setY(int newY);

}
